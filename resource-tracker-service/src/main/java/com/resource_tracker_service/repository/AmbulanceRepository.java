package com.resource_tracker_service.repository;

import com.resource_tracker_service.model.Ambulance;
import com.resource_tracker_service.model.enums.AmbulanceStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AmbulanceRepository extends JpaRepository<Ambulance, Long> {
    List<Ambulance> findByStatus(AmbulanceStatus status);

    boolean existsByVehicleNumber(String vehicleNumber);
}