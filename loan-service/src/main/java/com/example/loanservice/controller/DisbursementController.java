package com.example.loanservice.controller;

import com.example.loanservice.model.Disbursement;
import com.example.loanservice.service.DisbursementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/disbursements")
public class DisbursementController {
    @Autowired
    private DisbursementService service;

    @PostMapping("/{loanId}/disburse")
    public Disbursement disburse(@PathVariable Long loanId) {
        return service.disburseLoan(loanId);
    }
}