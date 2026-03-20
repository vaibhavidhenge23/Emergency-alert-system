package com.resource_tracker_service.dto.request;


import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class AmbulanceRequest {
    @NotBlank(message = "Vehicle number required")
    private String vehicleNumber;

    @NotBlank(message = "Driver name required")
    private String driverName;

    @NotBlank(message = "Driver phone required")
    private String driverPhone;

    private Double latitude;
    private Double longitude;
}
