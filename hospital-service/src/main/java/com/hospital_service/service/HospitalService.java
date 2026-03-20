package com.hospital_service.service;

import com.hospital_service.dto.request.BedUpdateRequest;
import com.hospital_service.dto.request.HospitalRequest;
import com.hospital_service.dto.response.HospitalResponse;

import java.util.List;

public interface HospitalService {
    HospitalResponse addHospital(HospitalRequest request);
    HospitalResponse getHospitalById(Long id);
    List<HospitalResponse> getAllHospitals();
    List<HospitalResponse> getHospitalsWithAvailableBeds();
    List<HospitalResponse> getHospitalsWithAvailableIcuBeds();
    HospitalResponse updateBedAvailability(Long id, BedUpdateRequest request);
    HospitalResponse findNearestHospitalWithBeds(Double latitude, Double longitude);
    void deleteHospital(Long id);
}
