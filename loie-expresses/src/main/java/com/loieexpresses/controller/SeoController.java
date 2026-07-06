package com.loieexpresses.controller;

import com.loieexpresses.service.SettingsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Hutoa robots.txt na sitemap.xml ili Google iweze kuipata website haraka.
 */
@RestController
@RequiredArgsConstructor
public class SeoController {

    private final SettingsService settingsService;

    @GetMapping(value = "/robots.txt", produces = MediaType.TEXT_PLAIN_VALUE)
    @ResponseBody
    public String robots() {
        String base = baseUrl();
        return "User-agent: *\n" +
               "Allow: /\n" +
               "Disallow: /admin\n" +
               "Sitemap: " + base + "/sitemap.xml\n";
    }

    @GetMapping(value = "/sitemap.xml", produces = MediaType.APPLICATION_XML_VALUE)
    @ResponseBody
    public String sitemap() {
        String base = baseUrl();
        String[] paths = {"/", "/about", "/services", "/fleet", "/gallery", "/news", "/contact", "/quote"};
        StringBuilder sb = new StringBuilder();
        sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
        sb.append("<urlset xmlns=\"http://www.sitemaps.org/schemas/sitemap/0.9\">\n");
        for (String p : paths) {
            sb.append("  <url><loc>").append(base).append(p).append("</loc></url>\n");
        }
        sb.append("</urlset>\n");
        return sb.toString();
    }

    private String baseUrl() {
        String url = settingsService.get().getSiteUrl();
        if (url == null || url.isBlank()) return "";
        return url.endsWith("/") ? url.substring(0, url.length() - 1) : url;
    }
}
