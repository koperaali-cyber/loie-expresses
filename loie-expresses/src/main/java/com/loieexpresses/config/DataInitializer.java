package com.loieexpresses.config;

import com.loieexpresses.entity.*;
import com.loieexpresses.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Seeds the database on first run:
 *  - a default SUPER_ADMIN account
 *  - website settings row
 *  - sample services so the public site is not empty
 *
 * Default login:  username = admin   password = admin123
 */
@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final WebsiteSettingsRepository settingsRepository;
    private final ServiceRepository serviceRepository;
    private final PasswordEncoder encoder;

    @Override
    public void run(String... args) {
        // Unda admin kama hayupo. Pia, ukiweka env RESET_ADMIN=true,
        // password ya admin itarudishwa kuwa 'admin123' kila mfumo unapoanza
        // (nzuri kama umesahau/imeharibika). Ondoa env hiyo baada ya kuingia.
        boolean resetAdmin = "true".equalsIgnoreCase(System.getenv("RESET_ADMIN"));
        User admin = userRepository.findByUsername("admin").orElse(null);

        if (admin == null) {
            userRepository.save(User.builder()
                    .username("admin")
                    .password(encoder.encode("admin123"))
                    .fullName("Super Administrator")
                    .email("admin@loieexpresses.co.tz")
                    .role(Role.SUPER_ADMIN)
                    .enabled(true)
                    .mustChangePassword(true)
                    .build());
        } else if (resetAdmin) {
            admin.setPassword(encoder.encode("admin123"));
            admin.setEnabled(true);
            admin.setRole(Role.SUPER_ADMIN);
            admin.setMustChangePassword(true);
            userRepository.save(admin);
        }

        if (settingsRepository.count() == 0) {
            settingsRepository.save(WebsiteSettings.builder()
                    .id(1L)
                    .companyName("LOIE EXPRESSES")
                    .tagline("Reliable Transport Services Across Tanzania")
                    .phone("+255 700 000 000")
                    .email("info@loieexpresses.co.tz")
                    .address("Dar es Salaam, Tanzania")
                    .heroTitle("Reliable transport, wherever Tanzania takes you")
                    .heroSubtitle("Special and private hire, transport business consultation, and affordable Bajaj & Bodaboda financing — arranged quickly and priced fairly in TZS.")
                    .aboutText("LOIE EXPRESSES is a Tanzanian transport company built on punctuality and fair pricing. We move people and goods safely across the country, and we help riders own their first Bajaj or Bodaboda through simple, affordable financing.")
                    .footerText("LOIE EXPRESSES — moving people, goods and opportunity across Tanzania.")
                    .whatsapp("+255700000000")
                    .googleMapEmbed("https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d63417.5!2d39.2!3d-6.8!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!5e0!3m2!1sen!2stz!4v1600000000000")
                    .seoTitle("LOIE EXPRESSES | Transport Services Tanzania")
                    .seoDescription("Transport hire and Bajaj/Bodaboda financing in Tanzania.")
                    .build());
        }

        if (serviceRepository.count() == 0) {
            serviceRepository.saveAll(List.of(
                svc("Special Hire", "special-hire", "fa-car-side",
                    "Dedicated vehicles for your special trips and events."),
                svc("Private Hire", "private-hire", "fa-user-tie",
                    "Comfortable private transport whenever you need it."),
                svc("Transport Business Consultation", "consultation", "fa-handshake",
                    "Expert advice to start and grow your transport business."),
                svc("Affordable Bajaj Financing", "bajaj-financing", "fa-motorcycle",
                    "Own a Bajaj with flexible, affordable financing plans."),
                svc("Affordable Bodaboda Financing", "bodaboda-financing", "fa-motorcycle",
                    "Get on the road with easy Bodaboda financing options.")
            ));
        }
    }

    private ServiceEntity svc(String title, String slug, String icon, String desc) {
        return ServiceEntity.builder()
                .title(title).slug(slug).icon(icon)
                .shortDescription(desc).description(desc)
                .benefits("Reliable service\nAffordable pricing\nTrusted by Tanzanians")
                .active(true).build();
    }
}
