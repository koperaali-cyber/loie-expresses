package com.loieexpresses.entity;

import jakarta.persistence.*;
import lombok.*;

/**
 * Single-row table holding editable site-wide content and settings.
 * Lets the admin manage the website without touching source code.
 */
@Entity
@Table(name = "website_settings")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class WebsiteSettings {

    @Id
    private Long id;   // always 1

    // Company info
    @Builder.Default private String companyName = "LOIE EXPRESSES";
    @Builder.Default private String tagline = "Reliable Transport Services Across Tanzania";
    private String logo;
    private String phone;
    private String email;
    private String address;

    // Hero
    private String heroTitle;
    private String heroSubtitle;
    private String heroImage;

    // About
    @Column(columnDefinition = "TEXT") private String aboutText;
    @Column(columnDefinition = "TEXT") private String footerText;

    // Social
    private String facebook;
    private String twitter;
    private String instagram;
    private String whatsapp;

    // Map + SEO
    @Column(columnDefinition = "TEXT") private String googleMapEmbed;
    private String seoTitle;
    @Column(length = 300) private String seoDescription;
    @Column(length = 300) private String seoKeywords;
    private String siteUrl;   // Anwani rasmi ya website, mfano https://loie-expresses.onrender.com
}
