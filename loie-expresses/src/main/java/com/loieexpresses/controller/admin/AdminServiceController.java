package com.loieexpresses.controller.admin;

import com.loieexpresses.entity.ServiceEntity;
import com.loieexpresses.repository.ServiceRepository;
import com.loieexpresses.util.FileStorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin/services")
@RequiredArgsConstructor
public class AdminServiceController {

    private final ServiceRepository repo;
    private final FileStorageService fileStorage;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("services", repo.findAll());
        return "admin/services";
    }

    @GetMapping("/form")
    public String form(@RequestParam(required = false) Long id, Model model) {
        model.addAttribute("service", id != null ? repo.findById(id).orElse(new ServiceEntity()) : new ServiceEntity());
        return "admin/service-form";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute ServiceEntity service,
                       @RequestParam(required = false) MultipartFile imageFile,
                       RedirectAttributes ra) {
        if (service.getId() != null) {
            ServiceEntity ex = repo.findById(service.getId()).orElse(null);
            if (ex != null && (imageFile == null || imageFile.isEmpty()))
                service.setFeaturedImage(ex.getFeaturedImage());
        }
        String img = fileStorage.store(imageFile);
        if (img != null) service.setFeaturedImage(img);
        repo.save(service);
        ra.addFlashAttribute("success", "Service saved.");
        return "redirect:/admin/services";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Long id, RedirectAttributes ra) {
        repo.deleteById(id);
        ra.addFlashAttribute("success", "Service deleted.");
        return "redirect:/admin/services";
    }
}
