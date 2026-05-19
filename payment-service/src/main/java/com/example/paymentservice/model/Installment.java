package com.example.paymentservice.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Installment {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long loanId; // MICROSERVICE CHANGE: We don't have the Loan object here!

    private Integer installmentNo;
    private Double amount;
    private LocalDate dueDate;
    private Boolean paidFlag = false;

    public Installment() {}

    public Installment(Long loanId, Integer installmentNo, Double amount, LocalDate dueDate) {
        this.loanId = loanId;
        this.installmentNo = installmentNo;
        this.amount = amount;
        this.dueDate = dueDate;
    }

    public Long getId() { return id; }
    public Long getLoanId() { return loanId; }
    public Integer getInstallmentNo() { return installmentNo; }
    public Double getAmount() { return amount; }
    public LocalDate getDueDate() { return dueDate; }
    public Boolean getPaidFlag() { return paidFlag; }
    public void setPaidFlag(Boolean paidFlag) { this.paidFlag = paidFlag; }
}