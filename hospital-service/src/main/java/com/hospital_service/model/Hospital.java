package com.hospital_service.model;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name= "hospital")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Hospital {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String address;

    private Double latitude;
    private Double longitude;

    @Column(nullable = false)
    private String contactNumber;

    private Integer totalBeds;
    private Integer availableBeds;
    private Integer icuBeds;
    private Integer availableIcuBeds;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}
