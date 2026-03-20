package com.hospital_service.dto.response;


import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class HospitalResponse {

    private Long id;
    private String name;
    private String address;
    private Double latitude;
    private Double longitude;
    private String contactNumber;
    private Integer totalBeds;
    private Integer availableBeds;
    private Integer icuBeds;
    private Integer availableIcuBeds;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
