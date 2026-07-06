package com.loieexpresses.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

/** An image in the gallery, optionally grouped into an album. */
@Entity
@Table(name = "gallery")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class GalleryImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 150)
    private String title;

    @Column(nullable = false)
    private String fileName;

    @Column(length = 100)
    private String album;

    private LocalDateTime uploadedAt;

    @PrePersist
    void onCreate() { this.uploadedAt = LocalDateTime.now(); }
}
