package com.loieexpresses.repository;

import com.loieexpresses.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    long countByStatus(com.loieexpresses.entity.RequestStatus status);
    List<Booking> findAllByOrderByCreatedAtDesc();
}
