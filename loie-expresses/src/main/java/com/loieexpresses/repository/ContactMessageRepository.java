package com.loieexpresses.repository;

import com.loieexpresses.entity.ContactMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;

public interface ContactMessageRepository extends JpaRepository<ContactMessage, Long> {
    List<ContactMessage> findAllByOrderByCreatedAtDesc();
    long countByReadFalse();
}
