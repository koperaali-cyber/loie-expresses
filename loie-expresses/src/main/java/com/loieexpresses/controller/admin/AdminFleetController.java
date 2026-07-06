package com.loieexpresses.controller.admin;

import com.loieexpresses.entity.Vehicle;
import com.loieexpresses.repository.VehicleRepository;
import com.loieexpresses.util.FileStorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin/fleet")
@RequiredArgsConstructor
public class AdminFleetController {

    private final VehicleRepository repo;
    private final FileStorageService fileStorage;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("vehicles", repo.findAll());
        return "admin/fleet";
    }

    @GetMapping("/form")
    public String form(@RequestParam(required = false) Long id, Model model) {
        model.addAttribute("vehicle", id != null ? repo.findById(id).orElse(new Vehicle()) : new Vehicle());
        return "admin/fleet-form";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Vehicle vehicle,
                       @RequestParam(required = false) MultipartFile imageFile,
                       RedirectAttributes ra) {
        if (vehicle.getId() != null) {
            Vehicle ex = repo.findById(vehicle.getId()).orElse(null);
            if (ex != null && (imageFile == null || imageFile.isEmpty()))
                vehicle.setImage(ex.getImage());
        }
        String img = fileStorage.store(imageFile);
        if (img != null) vehicle.setImage(img);
        repo.save(vehicle);
        ra.addFlashAttribute("success", "Vehicle saved.");
        return "redirect:/admin/fleet";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Long id, RedirectAttributes ra) {
        repo.deleteById(id);
        ra.addFlashAttribute("success", "Vehicle deleted.");
        return "redirect:/admin/fleet";
    }
}
