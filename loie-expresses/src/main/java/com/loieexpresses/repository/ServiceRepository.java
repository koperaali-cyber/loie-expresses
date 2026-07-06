package com.loieexpresses.repository;

import com.loieexpresses.entity.ServiceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;

public interface ServiceRepository extends JpaRepository<ServiceEntity, Long> {
    List<ServiceEntity> findByActiveTrue();
    Optional<ServiceEntity> findBySlug(String slug);
}
