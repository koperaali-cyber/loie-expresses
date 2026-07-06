package com.loieexpresses.controller;

import com.loieexpresses.entity.User;
import com.loieexpresses.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * Huruhusu admin aliyeingia kubadilisha password yake mwenyewe.
 * Ikiwa 'mustChangePassword' ni true (mara ya kwanza), analazimika kubadilisha.
 */
@Controller
@RequiredArgsConstructor
public class AccountController {

    private final UserRepository userRepository;
    private final PasswordEncoder encoder;

    @GetMapping("/admin/change-password")
    public String form(Authentication auth, Model model) {
        User user = userRepository.findByUsername(auth.getName()).orElseThrow();
        model.addAttribute("mustChange", user.isMustChangePassword());
        return "admin/change-password";
    }

    @PostMapping("/admin/change-password")
    public String change(Authentication auth,
                         @RequestParam String currentPassword,
                         @RequestParam String newPassword,
                         @RequestParam String confirmPassword,
                         Model model) {
        User user = userRepository.findByUsername(auth.getName()).orElseThrow();
        model.addAttribute("mustChange", user.isMustChangePassword());

        if (!encoder.matches(currentPassword, user.getPassword())) {
            model.addAttribute("error", "Current password is incorrect. / Password ya sasa si sahihi.");
            return "admin/change-password";
        }
        if (newPassword == null || newPassword.length() < 6) {
            model.addAttribute("error", "New password must be at least 6 characters. / Password mpya iwe herufi 6 au zaidi.");
            return "admin/change-password";
        }
        if (!newPassword.equals(confirmPassword)) {
            model.addAttribute("error", "Passwords do not match. / Password hazifanani.");
            return "admin/change-password";
        }

        user.setPassword(encoder.encode(newPassword));
        user.setMustChangePassword(false);
        userRepository.save(user);
        model.addAttribute("success", "Password changed successfully. / Password imebadilishwa.");
        model.addAttribute("mustChange", false);
        return "admin/change-password";
    }
}
