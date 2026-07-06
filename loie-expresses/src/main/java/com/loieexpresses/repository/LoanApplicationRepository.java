package com.loieexpresses.repository;

import com.loieexpresses.entity.LoanApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;

public interface LoanApplicationRepository extends JpaRepository<LoanApplication, Long> {
    List<LoanApplication> findByLoanTypeOrderByCreatedAtDesc(String loanType);
    List<LoanApplication> findAllByOrderByCreatedAtDesc();
}
