package com.example.paymentservice.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Payment {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "installment_id")
    private Installment installment;

    private Double amountPaid;
    private LocalDateTime paymentDate;
    private String paymentMethod;

    public Payment() {}

    public Payment(Installment installment, Double amountPaid, String paymentMethod) {
        this.installment = installment;
        this.amountPaid = amountPaid;
        this.paymentMethod = paymentMethod;
        this.paymentDate = LocalDateTime.now();
    }

    public Long getId() { return id; }
    public Installment getInstallment() { return installment; }
    public Double getAmountPaid() { return amountPaid; }
}