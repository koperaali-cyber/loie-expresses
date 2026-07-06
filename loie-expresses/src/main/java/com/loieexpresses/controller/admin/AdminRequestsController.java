package com.loieexpresses.controller.admin;

import com.loieexpresses.entity.RequestStatus;
import com.loieexpresses.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/** Manages bookings, quotes, loans, consultations and contact messages. */
@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminRequestsController {

    private final BookingRepository bookingRepo;
    private final QuoteRequestRepository quoteRepo;
    private final LoanApplicationRepository loanRepo;
    private final ConsultationRepository consultationRepo;
    private final ContactMessageRepository contactRepo;

    // ---- Bookings ----
    @GetMapping("/bookings")
    public String bookings(Model model) {
        model.addAttribute("bookings", bookingRepo.findAllByOrderByCreatedAtDesc());
        model.addAttribute("statuses", RequestStatus.values());
        return "admin/bookings";
    }

    @PostMapping("/bookings/status/{id}")
    public String bookingStatus(@PathVariable Long id, @RequestParam RequestStatus status, RedirectAttributes ra) {
        bookingRepo.findById(id).ifPresent(b -> { b.setStatus(status); bookingRepo.save(b); });
        ra.addFlashAttribute("success", "Booking status updated.");
        return "redirect:/admin/bookings";
    }

    // ---- Quotes ----
    @GetMapping("/quotes")
    public String quotes(Model model) {
        model.addAttribute("quotes", quoteRepo.findAllByOrderByCreatedAtDesc());
        model.addAttribute("statuses", RequestStatus.values());
        return "admin/quotes";
    }

    @PostMapping("/quotes/status/{id}")
    public String quoteStatus(@PathVariable Long id, @RequestParam RequestStatus status, RedirectAttributes ra) {
        quoteRepo.findById(id).ifPresent(q -> { q.setStatus(status); quoteRepo.save(q); });
        ra.addFlashAttribute("success", "Quote status updated.");
        return "redirect:/admin/quotes";
    }

    // ---- Loans ----
    @GetMapping("/loans")
    public String loans(Model model) {
        model.addAttribute("loans", loanRepo.findAllByOrderByCreatedAtDesc());
        model.addAttribute("statuses", RequestStatus.values());
        return "admin/loans";
    }

    @PostMapping("/loans/update/{id}")
    public String loanUpdate(@PathVariable Long id,
                             @RequestParam RequestStatus status,
                             @RequestParam(required = false) String notes,
                             RedirectAttributes ra) {
        loanRepo.findById(id).ifPresent(l -> { l.setStatus(status); l.setNotes(notes); loanRepo.save(l); });
        ra.addFlashAttribute("success", "Loan application updated.");
        return "redirect:/admin/loans";
    }

    // ---- Consultations ----
    @GetMapping("/consultations")
    public String consultations(Model model) {
        model.addAttribute("consultations", consultationRepo.findAllByOrderByCreatedAtDesc());
        model.addAttribute("statuses", RequestStatus.values());
        return "admin/consultations";
    }

    @PostMapping("/consultations/status/{id}")
    public String consultationStatus(@PathVariable Long id, @RequestParam RequestStatus status, RedirectAttributes ra) {
        consultationRepo.findById(id).ifPresent(c -> { c.setStatus(status); consultationRepo.save(c); });
        ra.addFlashAttribute("success", "Consultation status updated.");
        return "redirect:/admin/consultations";
    }

    // ---- Contact messages ----
    @GetMapping("/messages")
    public String messages(Model model) {
        model.addAttribute("messages", contactRepo.findAllByOrderByCreatedAtDesc());
        return "admin/messages";
    }

    @PostMapping("/messages/read/{id}")
    public String markRead(@PathVariable Long id, RedirectAttributes ra) {
        contactRepo.findById(id).ifPresent(m -> { m.setRead(true); contactRepo.save(m); });
        return "redirect:/admin/messages";
    }

    @PostMapping("/messages/delete/{id}")
    public String deleteMessage(@PathVariable Long id, RedirectAttributes ra) {
        contactRepo.deleteById(id);
        ra.addFlashAttribute("success", "Message deleted.");
        return "redirect:/admin/messages";
    }
}
