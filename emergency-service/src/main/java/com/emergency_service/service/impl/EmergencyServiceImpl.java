package com.emergency_service.service.impl;

import com.emergency_service.dto.request.EmergencyRequest;
import com.emergency_service.dto.response.EmergencyResponse;
import com.emergency_service.exception.ResourceNotFoundException;
import com.emergency_service.model.EmergencyCase;
import com.emergency_service.model.enums.EmergencyStatus;
import com.emergency_service.repository.EmergencyCaseRepository;
import com.emergency_service.service.EmergencyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmergencyServiceImpl implements EmergencyService {

    private final EmergencyCaseRepository repository;

    @Override
    public EmergencyResponse createEmergency(EmergencyRequest request) {
        log.info("Creating new emergency case for: {}", request.getCallerPhone());

        EmergencyCase emergencyCase = EmergencyCase.builder()
                .callerName(request.getCallerName())
                .callerPhone(request.getCallerPhone())
                .latitude(request.getLatitude())
                .longitude(request.getLongitude())
                .address(request.getAddress())
                .emergencyType(request.getEmergencyType())
                .description(request.getDescription())
                .status(EmergencyStatus.CREATED)
                .build();

        EmergencyCase saved = repository.save(emergencyCase);
        log.info("Emergency case created with ID: {}", saved.getId());
        return mapToResponse(saved);
    }

    @Override
    public EmergencyResponse getEmergencyById(Long id) {
        EmergencyCase emergencyCase = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Emergency case not found with id: " + id));
        return mapToResponse(emergencyCase);
    }

    @Override
    public List<EmergencyResponse> getAllEmergencies() {
        return repository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<EmergencyResponse> getEmergenciesByStatus(EmergencyStatus status) {
        return repository.findByStatus(status)
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public EmergencyResponse updateStatus(Long id, EmergencyStatus status) {
        EmergencyCase emergencyCase = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Emergency case not found with id: " + id));
        emergencyCase.setStatus(status);
        return mapToResponse(repository.save(emergencyCase));
    }

    @Override
    public EmergencyResponse assignAmbulance(Long id, Long ambulanceId) {
        EmergencyCase emergencyCase = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Emergency case not found with id: " + id));
        emergencyCase.setAssignedAmbulanceId(ambulanceId);
        emergencyCase.setStatus(EmergencyStatus.DISPATCHED);
        return mapToResponse(repository.save(emergencyCase));
    }

    @Override
    public EmergencyResponse assignHospital(Long id, Long hospitalId) {
        EmergencyCase emergencyCase = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Emergency case not found with id: " + id));
        emergencyCase.setAssignedHospitalId(hospitalId);
        return mapToResponse(repository.save(emergencyCase));
    }

    @Override
    public void deleteEmergency(Long id) {
        EmergencyCase emergencyCase = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Emergency case not found with id: " + id));
        repository.delete(emergencyCase);
    }

    // Entity to Response mapper
    private EmergencyResponse mapToResponse(EmergencyCase e) {
        return EmergencyResponse.builder()
                .id(e.getId())
                .callerName(e.getCallerName())
                .callerPhone(e.getCallerPhone())
                .latitude(e.getLatitude())
                .longitude(e.getLongitude())
                .address(e.getAddress())
                .emergencyType(e.getEmergencyType())
                .status(e.getStatus())
                .assignedAmbulanceId(e.getAssignedAmbulanceId())
                .assignedHospitalId(e.getAssignedHospitalId())
                .description(e.getDescription())
                .createdAt(e.getCreatedAt())
                .updatedAt(e.getUpdatedAt())
                .build();
    }
}