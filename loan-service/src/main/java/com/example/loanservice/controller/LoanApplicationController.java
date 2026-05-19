package com.example.loanservice.controller;

import com.example.loanservice.model.LoanApplication;
import com.example.loanservice.service.LoanApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/loans")
public class LoanApplicationController {
    @Autowired
    private LoanApplicationService service;

    @PostMapping("/apply")
    public LoanApplication apply(@RequestBody LoanApplication app) {
        return service.applyForLoan(app);
    }

    @PutMapping("/{id}/approve")
    public LoanApplication approve(@PathVariable Long id) {
        return service.approveLoan(id);
    }
    @GetMapping("/{id}")
    public LoanApplication getLoanById(@PathVariable Long id) {
        return service.getLoanById(id);
    }

    @GetMapping
    public List<LoanApplication> getAll() {
        return service.getAllLoans();
    }
}