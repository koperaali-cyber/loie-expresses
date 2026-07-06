package com.loieexpresses.controller;

import com.loieexpresses.entity.*;
import com.loieexpresses.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/** Receives submissions from the public website forms. */
@Controller
@RequestMapping("/submit")
@RequiredArgsConstructor
public class FormSubmissionController {

    private final BookingRepository bookingRepo;
    private final QuoteRequestRepository quoteRepo;
    private final LoanApplicationRepository loanRepo;
    private final ConsultationRepository consultationRepo;
    private final ContactMessageRepository contactRepo;

    @PostMapping("/booking")
    public String booking(Booking booking, RedirectAttributes ra) {
        bookingRepo.save(booking);
        ra.addFlashAttribute("success", "Your booking request has been received. We will contact you soon.");
        return "redirect:/quote";
    }

    @PostMapping("/quote")
    public String quote(QuoteRequest quote, RedirectAttributes ra) {
        quoteRepo.save(quote);
        ra.addFlashAttribute("success", "Your quote request has been submitted successfully.");
        return "redirect:/quote";
    }

    @PostMapping("/loan")
    public String loan(LoanApplication loan, RedirectAttributes ra) {
        loanRepo.save(loan);
        ra.addFlashAttribute("success", "Your financing application has been received.");
        return "redirect:/quote";
    }

    @PostMapping("/consultation")
    public String consultation(ConsultationRequest req, RedirectAttributes ra) {
        consultationRepo.save(req);
        ra.addFlashAttribute("success", "Your consultation request has been submitted.");
        return "redirect:/quote";
    }

    @PostMapping("/contact")
    public String contact(ContactMessage msg, RedirectAttributes ra) {
        contactRepo.save(msg);
        ra.addFlashAttribute("success", "Thank you for contacting us. We will reply shortly.");
        return "redirect:/contact";
    }
}
