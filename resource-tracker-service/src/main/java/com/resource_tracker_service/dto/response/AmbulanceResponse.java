package com.resource_tracker_service.dto.response;


import com.resource_tracker_service.model.enums.AmbulanceStatus;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class AmbulanceResponse {
    private Long id;
    private String vehicleNumber;
    private String driverName;
    private String driverPhone;
    private Double latitude;
    private Double longitude;
    private AmbulanceStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
