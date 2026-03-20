package com.hospital_service.service.impl;

import com.hospital_service.dto.request.BedUpdateRequest;
import com.hospital_service.dto.request.HospitalRequest;
import com.hospital_service.dto.response.HospitalResponse;
import com.hospital_service.exception.ResourceNotFoundException;
import com.hospital_service.model.Hospital;
import com.hospital_service.repository.HospitalRepository;
import com.hospital_service.service.HospitalService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
@Slf4j
public class HospitalServiceImpl  implements HospitalService {
    private final HospitalRepository repository;

    @Override
    public HospitalResponse addHospital(HospitalRequest request) {
        Hospital hospital = Hospital.builder()
                .name(request.getName())
                .address(request.getAddress())
                .latitude(request.getLatitude())
                .longitude(request.getLongitude())
                .contactNumber(request.getContactNumber())
                .totalBeds(request.getTotalBeds())
                .availableBeds(request.getAvailableBeds())
                .icuBeds(request.getIcuBeds())
                .availableIcuBeds(request.getAvailableIcuBeds())
                .build();
        return mapToResponse(repository.save(hospital));
    }

    @Override
    public HospitalResponse getHospitalById(Long id) {
        return mapToResponse(repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Hospital not found: " + id)));
    }

    @Override
    public List<HospitalResponse> getAllHospitals() {
        return repository.findAll().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<HospitalResponse> getHospitalsWithAvailableBeds() {
        return repository.findByAvailableBedsGreaterThan(0).stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<HospitalResponse> getHospitalsWithAvailableIcuBeds() {
        return repository.findByAvailableIcuBedsGreaterThan(0).stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public HospitalResponse updateBedAvailability(Long id, BedUpdateRequest request) {
        Hospital hospital = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Hospital not found: " + id));
        hospital.setAvailableBeds(request.getAvailableBeds());
        if (request.getAvailableIcuBeds() != null) {
            hospital.setAvailableIcuBeds(request.getAvailableIcuBeds());
        }
        return mapToResponse(repository.save(hospital));
    }

    @Override
    public HospitalResponse findNearestHospitalWithBeds(Double latitude, Double longitude) {
        List<Hospital> available = repository.findByAvailableBedsGreaterThan(0);
        if (available.isEmpty()) {
            throw new ResourceNotFoundException("No hospitals with available beds!");
        }
        return available.stream()
                .filter(h -> h.getLatitude() != null && h.getLongitude() != null)
                .min((a, b) -> {
                    double distA = calculateDistance(latitude, longitude, a.getLatitude(), a.getLongitude());
                    double distB = calculateDistance(latitude, longitude, b.getLatitude(), b.getLongitude());
                    return Double.compare(distA, distB);
                })
                .map(this::mapToResponse)
                .orElseThrow(() -> new ResourceNotFoundException("No hospital with location found!"));
    }

    @Override
    public void deleteHospital(Long id) {
        Hospital hospital = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Hospital not found: " + id));
        repository.delete(hospital);
    }

    private double calculateDistance(double lat1, double lon1, double lat2, double lon2) {
        final int R = 6371;
        double latDistance = Math.toRadians(lat2 - lat1);
        double lonDistance = Math.toRadians(lon2 - lon1);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return R * c;
    }

    private HospitalResponse mapToResponse(Hospital h) {
        return HospitalResponse.builder()
                .id(h.getId())
                .name(h.getName())
                .address(h.getAddress())
                .latitude(h.getLatitude())
                .longitude(h.getLongitude())
                .contactNumber(h.getContactNumber())
                .totalBeds(h.getTotalBeds())
                .availableBeds(h.getAvailableBeds())
                .icuBeds(h.getIcuBeds())
                .availableIcuBeds(h.getAvailableIcuBeds())
                .createdAt(h.getCreatedAt())
                .updatedAt(h.getUpdatedAt())
                .build();
    }

}
