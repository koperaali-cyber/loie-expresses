package com.loieexpresses.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

/** A message from the public Contact Us form. */
@Entity
@Table(name = "contact_messages")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class ContactMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 120)
    private String name;

    @Column(nullable = false, length = 120)
    private String email;

    @Column(length = 30)
    private String phone;

    @Column(length = 200)
    private String subject;

    @Column(columnDefinition = "TEXT")
    private String message;

    @Builder.Default
    private boolean read = false;

    private LocalDateTime createdAt;

    @PrePersist
    void onCreate() { this.createdAt = LocalDateTime.now(); }
}
