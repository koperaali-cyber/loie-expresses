package com.loieexpresses.controller;

import com.loieexpresses.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/** Serves all public-facing pages of the website. */
@Controller
@RequiredArgsConstructor
public class PublicController {

    private final ServiceRepository serviceRepo;
    private final VehicleRepository vehicleRepo;
    private final GalleryRepository galleryRepo;
    private final NewsRepository newsRepo;

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("services", serviceRepo.findByActiveTrue());
        model.addAttribute("vehicles", vehicleRepo.findAll());
        model.addAttribute("gallery", galleryRepo.findAll());
        model.addAttribute("news", newsRepo.findByPublishedTrueOrderByCreatedAtDesc());
        return "public/home";
    }

    @GetMapping("/about")
    public String about() { return "public/about"; }

    @GetMapping("/services")
    public String services(Model model) {
        model.addAttribute("services", serviceRepo.findByActiveTrue());
        return "public/services";
    }

    @GetMapping("/services/{slug}")
    public String serviceDetail(@PathVariable String slug, Model model) {
        model.addAttribute("service", serviceRepo.findBySlug(slug).orElse(null));
        return "public/service-detail";
    }

    @GetMapping("/fleet")
    public String fleet(Model model) {
        model.addAttribute("vehicles", vehicleRepo.findAll());
        return "public/fleet";
    }

    @GetMapping("/gallery")
    public String gallery(Model model) {
        model.addAttribute("gallery", galleryRepo.findAll());
        return "public/gallery";
    }

    @GetMapping("/news")
    public String news(Model model) {
        model.addAttribute("news", newsRepo.findByPublishedTrueOrderByCreatedAtDesc());
        return "public/news";
    }

    @GetMapping("/news/{slug}")
    public String newsDetail(@PathVariable String slug, Model model) {
        model.addAttribute("article", newsRepo.findBySlug(slug).orElse(null));
        return "public/news-detail";
    }

    @GetMapping("/contact")
    public String contact() { return "public/contact"; }

    @GetMapping("/quote")
    public String quote(Model model) {
        model.addAttribute("services", serviceRepo.findByActiveTrue());
        return "public/quote";
    }
}
