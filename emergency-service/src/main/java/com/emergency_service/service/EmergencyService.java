package com.emergency_service.service;

import com.emergency_service.dto.request.EmergencyRequest;
import com.emergency_service.dto.response.EmergencyResponse;
import com.emergency_service.model.enums.EmergencyStatus;

import java.util.List;

public interface EmergencyService {

    EmergencyResponse createEmergency(EmergencyRequest request);

    EmergencyResponse getEmergencyById(Long id);

    List<EmergencyResponse> getAllEmergencies();

    List<EmergencyResponse> getEmergenciesByStatus(EmergencyStatus status);

    EmergencyResponse updateStatus(Long id, EmergencyStatus status);

    EmergencyResponse assignAmbulance(Long id, Long ambulanceId);

    EmergencyResponse assignHospital(Long id, Long hospitalId);

    void deleteEmergency(Long id);
}