package com.loieexpresses.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

/** Request for a price quote. */
@Entity
@Table(name = "quote_requests")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class QuoteRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 120)
    private String customerName;

    @Column(nullable = false, length = 30)
    private String phone;

    @Column(length = 120)
    private String email;

    @Column(length = 120)
    private String serviceType;

    @Column(columnDefinition = "TEXT")
    private String details;

    @Enumerated(EnumType.STRING)
    @Builder.Default
    private RequestStatus status = RequestStatus.PENDING;

    private LocalDateTime createdAt;

    @PrePersist
    void onCreate() { this.createdAt = LocalDateTime.now(); }
}
