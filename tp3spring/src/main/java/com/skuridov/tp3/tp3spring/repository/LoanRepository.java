package com.skuridov.tp3.tp3spring.repository;

import com.skuridov.tp3.tp3spring.model.Loan.Loan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LoanRepository extends JpaRepository<Loan, Long> {
    List<Loan> findAllByBorrowerId(long id);
}
