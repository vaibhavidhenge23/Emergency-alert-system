package com.emergency_service.dto.request;

import com.emergency_service.model.enums.EmergencyType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class EmergencyRequest {

    @NotBlank(message = "Caller name required")
    private String callerName;

    @NotBlank(message = "Caller phone required")
    private String callerPhone;

    @NotNull(message = "Latitude required")
    private Double latitude;

    @NotNull(message = "Longitude required")
    private Double longitude;

    @NotBlank(message = "Address required")
    private String address;

    @NotNull(message = "Emergency type required")
    private EmergencyType emergencyType;

    private String description;
}