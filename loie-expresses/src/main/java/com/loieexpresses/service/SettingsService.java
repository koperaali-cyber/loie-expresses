package com.loieexpresses.service;

import com.loieexpresses.entity.WebsiteSettings;
import com.loieexpresses.repository.WebsiteSettingsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/** Provides the single WebsiteSettings row used across the site. */
@Service
@RequiredArgsConstructor
public class SettingsService {

    private final WebsiteSettingsRepository repo;

    public WebsiteSettings get() {
        return repo.findById(1L).orElseGet(() -> repo.save(
                WebsiteSettings.builder().id(1L).companyName("LOIE EXPRESSES").build()));
    }

    public WebsiteSettings save(WebsiteSettings s) {
        s.setId(1L);
        return repo.save(s);
    }
}
