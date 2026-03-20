package com.resource_tracker_service.model;


import jakarta.persistence.*;
import com.resource_tracker_service.model.enums.AmbulanceStatus;
import kotlin.BuilderInference;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name="ambulance")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Ambulance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(nullable = false, unique = true)
    private String vehicleNumber;

    @Column(nullable = false)
    private String driverName;
    @Column(nullable = false)
    private String driverPhone;

    // Live GPS location
    private Double latitude;
    private Double longitude;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AmbulanceStatus status;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
        if (status == null) status = AmbulanceStatus.AVAILABLE;
    }

    @PreUpdate
    protected void onUpdate() {

        updatedAt = LocalDateTime.now();
    }
}
