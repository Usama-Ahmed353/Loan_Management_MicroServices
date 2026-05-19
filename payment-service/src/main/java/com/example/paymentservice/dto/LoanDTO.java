package com.example.paymentservice.dto;

public class LoanDTO {
    private Long id;
    private Double loanAmount;
    private Integer tenureMonths;
    private String status;

    public Long getId() { return id; }
    public Double getLoanAmount() { return loanAmount; }
    public Integer getTenureMonths() { return tenureMonths; }
    public String getStatus() { return status; }
}