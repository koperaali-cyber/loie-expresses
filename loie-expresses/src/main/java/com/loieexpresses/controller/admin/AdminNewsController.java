package com.loieexpresses.controller.admin;

import com.loieexpresses.entity.News;
import com.loieexpresses.repository.NewsRepository;
import com.loieexpresses.util.FileStorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin/news")
@RequiredArgsConstructor
public class AdminNewsController {

    private final NewsRepository repo;
    private final FileStorageService fileStorage;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("news", repo.findAll());
        return "admin/news";
    }

    @GetMapping("/form")
    public String form(@RequestParam(required = false) Long id, Model model) {
        model.addAttribute("article", id != null ? repo.findById(id).orElse(new News()) : new News());
        return "admin/news-form";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute News news,
                       @RequestParam(required = false) MultipartFile imageFile,
                       RedirectAttributes ra) {
        if (news.getSlug() == null || news.getSlug().isBlank())
            news.setSlug(news.getTitle().toLowerCase().replaceAll("[^a-z0-9]+", "-").replaceAll("(^-|-$)", ""));
        if (news.getId() != null) {
            News ex = repo.findById(news.getId()).orElse(null);
            if (ex != null && (imageFile == null || imageFile.isEmpty())) news.setImage(ex.getImage());
        }
        String img = fileStorage.store(imageFile);
        if (img != null) news.setImage(img);
        repo.save(news);
        ra.addFlashAttribute("success", "Article saved.");
        return "redirect:/admin/news";
    }

    @PostMapping("/toggle/{id}")
    public String togglePublish(@PathVariable Long id, RedirectAttributes ra) {
        repo.findById(id).ifPresent(n -> { n.setPublished(!n.isPublished()); repo.save(n); });
        ra.addFlashAttribute("success", "Publish status updated.");
        return "redirect:/admin/news";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Long id, RedirectAttributes ra) {
        repo.deleteById(id);
        ra.addFlashAttribute("success", "Article deleted.");
        return "redirect:/admin/news";
    }
}
