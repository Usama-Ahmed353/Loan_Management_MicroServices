package com.example.paymentservice.repository;

import com.example.paymentservice.model.Installment;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface InstallmentRepository extends JpaRepository<Installment, Long> {
    List<Installment> findByLoanId(Long loanId);
    List<Installment> findByLoanIdAndPaidFlagFalse(Long loanId);
}