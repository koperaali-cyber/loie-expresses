package com.loieexpresses.controller.admin;

import com.loieexpresses.entity.WebsiteSettings;
import com.loieexpresses.service.SettingsService;
import com.loieexpresses.util.FileStorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/** Edit website content: homepage, about, contact, footer, hero, SEO, logo. */
@Controller
@RequestMapping("/admin/settings")
@RequiredArgsConstructor
public class AdminSettingsController {

    private final SettingsService settingsService;
    private final FileStorageService fileStorage;

    @GetMapping
    public String edit(Model model) {
        model.addAttribute("form", settingsService.get());
        return "admin/settings";
    }

    @PostMapping
    public String save(@ModelAttribute("form") WebsiteSettings form,
                       @RequestParam(required = false) MultipartFile logoFile,
                       @RequestParam(required = false) MultipartFile heroFile,
                       RedirectAttributes ra) {
        WebsiteSettings current = settingsService.get();
        String logo = fileStorage.store(logoFile);
        String hero = fileStorage.store(heroFile);
        if (logo != null) form.setLogo(logo); else form.setLogo(current.getLogo());
        if (hero != null) form.setHeroImage(hero); else form.setHeroImage(current.getHeroImage());
        settingsService.save(form);
        ra.addFlashAttribute("success", "Website settings updated successfully.");
        return "redirect:/admin/settings";
    }
}
