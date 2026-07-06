package com.loieexpresses.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

/** Transport business consultation request. */
@Entity
@Table(name = "consultation_requests")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class ConsultationRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 120)
    private String customerName;

    @Column(nullable = false, length = 30)
    private String phone;

    @Column(length = 120)
    private String email;

    @Column(length = 150)
    private String topic;

    @Column(columnDefinition = "TEXT")
    private String message;

    @Enumerated(EnumType.STRING)
    @Builder.Default
    private RequestStatus status = RequestStatus.PENDING;

    private LocalDateTime createdAt;

    @PrePersist
    void onCreate() { this.createdAt = LocalDateTime.now(); }
}
