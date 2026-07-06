package com.loieexpresses.repository;

import com.loieexpresses.entity.QuoteRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;

public interface QuoteRequestRepository extends JpaRepository<QuoteRequest, Long> {
    List<QuoteRequest> findAllByOrderByCreatedAtDesc();
}
