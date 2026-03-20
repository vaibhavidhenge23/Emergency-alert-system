package com.hospital_service.dto.request;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class HospitalRequest {

    @NotBlank(message = "Hospital name required")
    private String name;

    @NotBlank(message = "Address required")
    private String address;

    private Double latitude;
    private Double longitude;

    @NotBlank(message = "Contact number required")
    private String contactNumber;

    @NotNull(message = "Total beds required")
    private Integer totalBeds;

    private Integer availableBeds;
    private Integer icuBeds;
    private Integer availableIcuBeds;


}
