package com.resource_tracker_service.service.impl;


import java.util.List;
import java.util.stream.Collectors;

import com.resource_tracker_service.dto.request.AmbulanceRequest;
import com.resource_tracker_service.dto.request.LocationUpdateRequest;
import com.resource_tracker_service.dto.response.AmbulanceResponse;
import com.resource_tracker_service.exception.ResourceNotFoundException;
import com.resource_tracker_service.model.Ambulance;
import com.resource_tracker_service.model.enums.AmbulanceStatus;
import com.resource_tracker_service.repository.AmbulanceRepository;
import com.resource_tracker_service.service.AmbulanceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
@Slf4j
public class AmbulanceServiceImpl implements AmbulanceService {
    private final AmbulanceRepository repository;

    @Override
    public AmbulanceResponse registerAmbulance(AmbulanceRequest request) {
        Ambulance ambulance = Ambulance.builder()
                .vehicleNumber(request.getVehicleNumber())
                .driverName(request.getDriverName())
                .driverPhone(request.getDriverPhone())
                .latitude(request.getLatitude())
                .longitude(request.getLongitude())
                .status(AmbulanceStatus.AVAILABLE)
                .build();
        return mapToResponse(repository.save(ambulance));
    }

    @Override
    public AmbulanceResponse getAmbulanceById(Long id) {
        return mapToResponse(repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Ambulance not found: " + id)));
    }

    @Override
    public List<AmbulanceResponse> getAllAmbulances() {
        return repository.findAll().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<AmbulanceResponse> getAmbulancesByStatus(AmbulanceStatus status) {
        return repository.findByStatus(status).stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public AmbulanceResponse updateLocation(Long id, LocationUpdateRequest request) {
        Ambulance ambulance = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Ambulance not found: " + id));
        ambulance.setLatitude(request.getLatitude());
        ambulance.setLongitude(request.getLongitude());
        return mapToResponse(repository.save(ambulance));
    }

    @Override
    public AmbulanceResponse updateStatus(Long id, AmbulanceStatus status) {
        Ambulance ambulance = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Ambulance not found: " + id));
        ambulance.setStatus(status);
        return mapToResponse(repository.save(ambulance));
    }

    @Override
    public AmbulanceResponse findNearestAvailableAmbulance(Double latitude, Double longitude) {
        List<Ambulance> available = repository.findByStatus(AmbulanceStatus.AVAILABLE);
        if (available.isEmpty()) {
            throw new ResourceNotFoundException("No available ambulances found!");
        }

        // Haversine formula — nearest ambulance find karo
        return available.stream()
                .filter(a -> a.getLatitude() != null && a.getLongitude() != null)
                .min((a, b) -> {
                    double distA = calculateDistance(latitude, longitude, a.getLatitude(), a.getLongitude());
                    double distB = calculateDistance(latitude, longitude, b.getLatitude(), b.getLongitude());
                    return Double.compare(distA, distB);
                })
                .map(this::mapToResponse)
                .orElseThrow(() -> new ResourceNotFoundException("No ambulance with location found!"));
    }

    @Override
    public void deleteAmbulance(Long id) {
        Ambulance ambulance = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Ambulance not found: " + id));
        repository.delete(ambulance);
    }

    // Haversine formula — distance in KM
    private double calculateDistance(double lat1, double lon1, double lat2, double lon2) {
        final int R = 6371;
        double latDistance = Math.toRadians(lat2 - lat1);
        double lonDistance = Math.toRadians(lon2 - lon1);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return R * c;
    }

    private AmbulanceResponse mapToResponse(Ambulance a) {
        return AmbulanceResponse.builder()
                .id(a.getId())
                .vehicleNumber(a.getVehicleNumber())
                .driverName(a.getDriverName())
                .driverPhone(a.getDriverPhone())
                .latitude(a.getLatitude())
                .longitude(a.getLongitude())
                .status(a.getStatus())
                .createdAt(a.getCreatedAt())
                .updatedAt(a.getUpdatedAt())
                .build();
    }

}
