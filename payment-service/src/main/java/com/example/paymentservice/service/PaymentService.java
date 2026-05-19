package com.example.paymentservice.service;

import com.example.paymentservice.client.LoanClient;
import com.example.paymentservice.dto.LoanDTO;
import com.example.paymentservice.model.Installment;
import com.example.paymentservice.model.Payment;
import com.example.paymentservice.repository.InstallmentRepository;
import com.example.paymentservice.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class PaymentService {
    @Autowired
    private InstallmentRepository installmentRepo;
    @Autowired
    private PaymentRepository paymentRepo;
    @Autowired
    private LoanClient loanClient;

    public List<Installment> generateInstallments(Long loanId) {
        // MICROSERVICE MAGIC: Fetching Loan rules from another service over HTTP!
        LoanDTO loan = loanClient.getLoanById(loanId);

        Double amountPerInstallment = loan.getLoanAmount() / loan.getTenureMonths();
        List<Installment> installments = new ArrayList<>();

        for (int i = 1; i <= loan.getTenureMonths(); i++) {
            Installment inst = new Installment(
                    loanId, i, amountPerInstallment, LocalDate.now().plusMonths(i)
            );
            installments.add(installmentRepo.save(inst));
        }
        return installments;
    }

    public Payment makePayment(Long installmentId, Double amount, String method) {
        Installment installment = installmentRepo.findById(installmentId)
                .orElseThrow(() -> new RuntimeException("Installment not found"));

        if (installment.getPaidFlag()) throw new RuntimeException("Already paid!");

        Payment payment = new Payment(installment, amount, method);
        paymentRepo.save(payment);

        // Exact logic from your original code using compareTo for doubles!
        if (amount.compareTo(installment.getAmount()) == 0 || amount > installment.getAmount()) {
            installment.setPaidFlag(true);
            installmentRepo.save(installment);
        }
        return payment;
    }
}