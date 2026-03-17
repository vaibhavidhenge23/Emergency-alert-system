package com.emergency_service.dto.response;


import com.emergency_service.model.enums.EmergencyStatus;
import com.emergency_service.model.enums.EmergencyType;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class EmergencyResponse {

    private Long id;
    private String callerName;
    private String callerPhone;
            private Double latitude;
    private Double longitude;
    private String address;
    private EmergencyType emergencyType;
    private EmergencyStatus status;
    private Long assignedAmbulanceId;
    private Long assignedHospitalId;
    private String description;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;


}
