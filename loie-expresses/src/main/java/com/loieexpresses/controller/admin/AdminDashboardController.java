package com.loieexpresses.controller.admin;

import com.loieexpresses.repository.*;
import com.loieexpresses.service.StatsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminDashboardController {

    private final StatsService statsService;
    private final BookingRepository bookingRepo;
    private final ContactMessageRepository contactRepo;

    @GetMapping({"", "/", "/dashboard"})
    public String dashboard(Model model) {
        model.addAttribute("stats", statsService.dashboardStats());
        model.addAttribute("chartData", statsService.bookingsByStatus());
        model.addAttribute("recentBookings", bookingRepo.findAllByOrderByCreatedAtDesc());
        model.addAttribute("recentMessages", contactRepo.findAllByOrderByCreatedAtDesc());
        return "admin/dashboard";
    }
}
