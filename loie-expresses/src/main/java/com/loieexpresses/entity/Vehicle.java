package com.loieexpresses.entity;

import jakarta.persistence.*;
import lombok.*;

/** A vehicle in the company fleet. */
@Entity
@Table(name = "fleet")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 150)
    private String name;

    @Column(length = 80)
    private String type;          // Bus, Bajaj, Bodaboda, Car...

    @Column(length = 80)
    private String plateNumber;

    private Integer capacity;

    @Column(columnDefinition = "TEXT")
    private String description;

    private String image;

    @Builder.Default
    private boolean available = true;
}
