package com.example.loanservice.service;

import com.example.loanservice.model.Disbursement;
import com.example.loanservice.model.LoanApplication;
import com.example.loanservice.repository.DisbursementRepository;
import com.example.loanservice.repository.LoanApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DisbursementService {
    @Autowired
    private DisbursementRepository disbursementRepo;

    @Autowired
    private LoanApplicationRepository loanRepo;

    public Disbursement disburseLoan(Long loanId) {
        LoanApplication app = loanRepo.findById(loanId).orElseThrow();
        if (app.getStatus() != LoanApplication.LoanStatus.APPROVED) {
            throw new RuntimeException("Loan must be approved first!");
        }

        app.setStatus(LoanApplication.LoanStatus.DISBURSED);
        loanRepo.save(app);

        Disbursement disbursement = new Disbursement(app, app.getLoanAmount());
        return disbursementRepo.save(disbursement);
    }
}