package com.loieexpresses.service;

import com.loieexpresses.entity.RequestStatus;
import com.loieexpresses.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

/** Aggregates counts for the admin dashboard statistics and charts. */
@Service
@RequiredArgsConstructor
public class StatsService {

    private final BookingRepository bookingRepo;
    private final QuoteRequestRepository quoteRepo;
    private final LoanApplicationRepository loanRepo;
    private final ConsultationRepository consultationRepo;
    private final ContactMessageRepository contactRepo;
    private final ServiceRepository serviceRepo;
    private final VehicleRepository vehicleRepo;
    private final NewsRepository newsRepo;

    public Map<String, Object> dashboardStats() {
        Map<String, Object> m = new LinkedHashMap<>();
        m.put("bookings", bookingRepo.count());
        m.put("quotes", quoteRepo.count());
        m.put("loans", loanRepo.count());
        m.put("consultations", consultationRepo.count());
        m.put("messages", contactRepo.count());
        m.put("unreadMessages", contactRepo.countByReadFalse());
        m.put("services", serviceRepo.count());
        m.put("vehicles", vehicleRepo.count());
        m.put("news", newsRepo.count());
        m.put("pendingBookings", bookingRepo.countByStatus(RequestStatus.PENDING));
        return m;
    }

    /** Data for the bookings-by-status chart. */
    public Map<String, Long> bookingsByStatus() {
        Map<String, Long> m = new LinkedHashMap<>();
        for (RequestStatus s : RequestStatus.values()) {
            m.put(s.name(), bookingRepo.countByStatus(s));
        }
        return m;
    }
}
