package com.loieexpresses.repository;

import com.loieexpresses.entity.ConsultationRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;

public interface ConsultationRepository extends JpaRepository<ConsultationRequest, Long> {
    List<ConsultationRequest> findAllByOrderByCreatedAtDesc();
}
