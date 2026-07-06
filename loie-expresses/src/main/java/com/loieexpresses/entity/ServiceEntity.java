package com.loieexpresses.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

/** A service offered by LOIE EXPRESSES (Special Hire, Bajaj Financing, etc.). */
@Entity
@Table(name = "services")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class ServiceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 150)
    private String title;

    @Column(length = 200)
    private String slug;

    @Column(length = 300)
    private String shortDescription;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(columnDefinition = "TEXT")
    private String benefits;

    private String icon;          // Font Awesome class, e.g. "fa-car"
    private String featuredImage; // uploaded file name

    @Builder.Default
    private boolean active = true;

    private LocalDateTime createdAt;

    @PrePersist
    void onCreate() { this.createdAt = LocalDateTime.now(); }
}
