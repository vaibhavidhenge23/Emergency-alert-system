package com.emergency_service.model;

import com.emergency_service.model.enums.EmergencyStatus;
import com.emergency_service.model.enums.EmergencyType;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "emergency_cases")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmergencyCase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String callerName;

    @Column(nullable = false)
    private String callerPhone;

    // Latitude & Longitude of emergency location
    @Column(nullable = false)
    private Double latitude;

    @Column(nullable = false)
    private Double longitude;

    @Column(nullable = false)
    private String address;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EmergencyType emergencyType;  // MEDICAL, FIRE, ACCIDENT

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EmergencyStatus status;  // CREATED, DISPATCHED, IN_PROGRESS, COMPLETED, CANCELLED

    // Assigned ambulance ID (from resource-tracker-service)
    private Long assignedAmbulanceId;

    // Assigned hospital ID (from hospital-service)
    private Long assignedHospitalId;

    private String description;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
        if (status == null) status = EmergencyStatus.CREATED;
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}