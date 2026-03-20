package com.resource_tracker_service.service;


import com.resource_tracker_service.dto.request.AmbulanceRequest;
import com.resource_tracker_service.dto.request.LocationUpdateRequest;
import com.resource_tracker_service.dto.response.AmbulanceResponse;
import com.resource_tracker_service.model.enums.AmbulanceStatus;

import java.util.List;

public interface AmbulanceService {

    AmbulanceResponse registerAmbulance(AmbulanceRequest request);
    AmbulanceResponse getAmbulanceById(Long id);
    List<AmbulanceResponse> getAllAmbulances();
    List<AmbulanceResponse> getAmbulancesByStatus(AmbulanceStatus status);
    AmbulanceResponse updateLocation(Long id, LocationUpdateRequest request);
    AmbulanceResponse updateStatus(Long id, AmbulanceStatus status);
    AmbulanceResponse findNearestAvailableAmbulance(Double latitude, Double longitude);
    void deleteAmbulance(Long id);

}
