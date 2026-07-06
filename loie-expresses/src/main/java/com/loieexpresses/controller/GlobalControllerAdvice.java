package com.loieexpresses.controller;

import com.loieexpresses.service.SettingsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

/** Makes website settings available to every Thymeleaf template as ${settings}. */
@ControllerAdvice
@RequiredArgsConstructor
public class GlobalControllerAdvice {

    private final SettingsService settingsService;

    @ModelAttribute("settings")
    public Object settings() {
        return settingsService.get();
    }
}
