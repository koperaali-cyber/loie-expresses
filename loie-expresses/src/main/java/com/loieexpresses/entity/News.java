package com.loieexpresses.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

/** News / announcement article. */
@Entity
@Table(name = "news")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class News {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 200)
    private String title;

    @Column(length = 250)
    private String slug;

    @Column(length = 300)
    private String summary;

    @Column(columnDefinition = "TEXT")
    private String content;

    private String image;
    private String author;

    @Builder.Default
    private boolean published = false;

    private LocalDateTime createdAt;

    @PrePersist
    void onCreate() { this.createdAt = LocalDateTime.now(); }
}
