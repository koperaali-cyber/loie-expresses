package com.loieexpresses.controller;

import com.loieexpresses.entity.User;
import com.loieexpresses.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.UUID;

/** Handles login page, forgot-password and reset-password flows. */
@Controller
@RequiredArgsConstructor
public class AuthController {

    private final UserRepository userRepository;
    private final PasswordEncoder encoder;

    @GetMapping("/login")
    public String login() { return "login"; }

    @GetMapping("/forgot-password")
    public String forgotPasswordForm() { return "forgot-password"; }

    @PostMapping("/forgot-password")
    public String forgotPassword(@RequestParam String username, Model model) {
        userRepository.findByUsername(username).ifPresent(u -> {
            u.setResetToken(UUID.randomUUID().toString());
            u.setResetTokenExpiry(LocalDateTime.now().plusHours(1));
            userRepository.save(u);
            // In production this token would be emailed. For dev we display it.
            model.addAttribute("token", u.getResetToken());
        });
        model.addAttribute("message",
                "If the account exists, a reset link has been generated. (Dev mode shows the token below.)");
        return "forgot-password";
    }

    @GetMapping("/reset-password")
    public String resetPasswordForm(@RequestParam String token, Model model) {
        model.addAttribute("token", token);
        return "reset-password";
    }

    @PostMapping("/reset-password")
    public String resetPassword(@RequestParam String token,
                                @RequestParam String password,
                                Model model) {
        User user = userRepository.findByResetToken(token).orElse(null);
        if (user == null || user.getResetTokenExpiry() == null
                || user.getResetTokenExpiry().isBefore(LocalDateTime.now())) {
            model.addAttribute("error", "Invalid or expired token.");
            model.addAttribute("token", token);
            return "reset-password";
        }
        user.setPassword(encoder.encode(password));
        user.setResetToken(null);
        user.setResetTokenExpiry(null);
        userRepository.save(user);
        return "redirect:/login?reset";
    }
}
