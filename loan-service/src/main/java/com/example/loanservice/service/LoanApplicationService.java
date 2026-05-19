package com.example.loanservice.service;

import com.example.loanservice.client.CustomerClient;
import com.example.loanservice.model.LoanApplication;
import com.example.loanservice.repository.LoanApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoanApplicationService {
    @Autowired
    private LoanApplicationRepository repo;

    @Autowired
    private CustomerClient customerClient; // Using Feign to talk to Customer Service!

    public LoanApplication applyForLoan(LoanApplication application) {
        try {
            // Verify if customer exists by calling Customer Service
            customerClient.getCustomerById(application.getCustomerId());
        } catch (Exception e) {
            throw new RuntimeException("Customer not found or Customer Service is down!");
        }
        return repo.save(application);
    }
    public LoanApplication getLoanById(Long id) {
        return repo.findById(id).orElseThrow(() -> new RuntimeException("Loan not found"));
    }

    public LoanApplication approveLoan(Long id) {
        LoanApplication app = repo.findById(id).orElseThrow();
        app.setStatus(LoanApplication.LoanStatus.APPROVED);
        return repo.save(app);
    }

    public List<LoanApplication> getAllLoans() {
        return repo.findAll();
    }
}