package com.loieexpresses.controller.admin;

import com.loieexpresses.entity.GalleryImage;
import com.loieexpresses.repository.GalleryRepository;
import com.loieexpresses.util.FileStorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin/gallery")
@RequiredArgsConstructor
public class AdminGalleryController {

    private final GalleryRepository repo;
    private final FileStorageService fileStorage;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("gallery", repo.findAll());
        return "admin/gallery";
    }

    @PostMapping("/upload")
    public String upload(@RequestParam String title,
                         @RequestParam(required = false) String album,
                         @RequestParam MultipartFile imageFile,
                         RedirectAttributes ra) {
        String img = fileStorage.store(imageFile);
        if (img != null) {
            repo.save(GalleryImage.builder().title(title).album(album).fileName(img).build());
            ra.addFlashAttribute("success", "Image uploaded.");
        } else {
            ra.addFlashAttribute("error", "Please choose an image.");
        }
        return "redirect:/admin/gallery";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Long id, RedirectAttributes ra) {
        repo.findById(id).ifPresent(g -> fileStorage.delete(g.getFileName()));
        repo.deleteById(id);
        ra.addFlashAttribute("success", "Image deleted.");
        return "redirect:/admin/gallery";
    }
}
