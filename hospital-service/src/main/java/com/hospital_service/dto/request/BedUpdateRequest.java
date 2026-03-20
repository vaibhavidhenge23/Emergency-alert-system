package com.hospital_service.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class BedUpdateRequest {
    @NotNull(message = "Available beds required")
    private Integer availableBeds;

    private Integer availableIcuBeds;
}
