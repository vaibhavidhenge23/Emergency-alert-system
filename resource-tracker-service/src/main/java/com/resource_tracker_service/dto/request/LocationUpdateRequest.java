package com.resource_tracker_service.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class LocationUpdateRequest {
    @NotNull(message = "Latitude required")
    private Double latitude;

    @NotNull(message = "Longitude required")
    private Double longitude;
}
