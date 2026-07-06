package com.loieexpresses.controller.admin;

import com.loieexpresses.entity.*;
import com.loieexpresses.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/** User management - restricted to SUPER_ADMIN via SecurityConfig. */
@Controller
@RequestMapping("/admin/users")
@RequiredArgsConstructor
public class AdminUserController {

    private final UserRepository userRepository;
    private final PasswordEncoder encoder;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("users", userRepository.findAll());
        model.addAttribute("roles", Role.values());
        return "admin/users";
    }

    @PostMapping("/save")
    public String save(@RequestParam(required = false) Long id,
                       @RequestParam String username,
                       @RequestParam String fullName,
                       @RequestParam(required = false) String email,
                       @RequestParam Role role,
                       @RequestParam(required = false) String password,
                       RedirectAttributes ra) {
        User user = (id != null) ? userRepository.findById(id).orElse(new User()) : new User();
        boolean isNew = (user.getId() == null);
        user.setUsername(username);
        user.setFullName(fullName);
        user.setEmail(email);
        user.setRole(role);
        if (password != null && !password.isBlank()) {
            user.setPassword(encoder.encode(password));
        } else if (user.getPassword() == null) {
            user.setPassword(encoder.encode("changeme123"));
        }
        user.setEnabled(true);
        if (isNew) {
            user.setMustChangePassword(true);
        }
        userRepository.save(user);
        ra.addFlashAttribute("success", "User saved.");
        return "redirect:/admin/users";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Long id, RedirectAttributes ra) {
        userRepository.deleteById(id);
        ra.addFlashAttribute("success", "User deleted.");
        return "redirect:/admin/users";
    }
}
