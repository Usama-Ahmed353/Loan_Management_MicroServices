package com.example.paymentservice.controller;

import com.example.paymentservice.model.Installment;
import com.example.paymentservice.model.Payment;
import com.example.paymentservice.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {
    @Autowired
    private PaymentService service;

    @PostMapping("/generate/{loanId}")
    public List<Installment> generateInstallments(@PathVariable Long loanId) {
        return service.generateInstallments(loanId);
    }

    @PostMapping("/pay")
    public Payment makePayment(@RequestParam Long installmentId,
                               @RequestParam Double amount,
                               @RequestParam String method) {
        return service.makePayment(installmentId, amount, method);
    }
}