package com.example.loanservice.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Disbursement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "loan_application_id")
    private LoanApplication loanApplication;

    private Double amountDisbursed;
    private LocalDateTime disbursementDate;

    public Disbursement() {}

    public Disbursement(LoanApplication loanApplication, Double amountDisbursed) {
        this.loanApplication = loanApplication;
        this.amountDisbursed = amountDisbursed;
        this.disbursementDate = LocalDateTime.now();
    }

    public Long getId() { return id; }
    public LoanApplication getLoanApplication() { return loanApplication; }
    public Double getAmountDisbursed() { return amountDisbursed; }
    public LocalDateTime getDisbursementDate() { return disbursementDate; }
}