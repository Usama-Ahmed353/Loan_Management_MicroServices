package com.example.paymentservice.client;

import com.example.paymentservice.dto.LoanDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "loan-service")
public interface LoanClient {
    @GetMapping("/api/loans/{id}")
    LoanDTO getLoanById(@PathVariable("id") Long id);
}