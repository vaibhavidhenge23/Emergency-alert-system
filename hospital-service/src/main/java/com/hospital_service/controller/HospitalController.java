package com.hospital_service.controller;

import com.hospital_service.dto.request.BedUpdateRequest;
import com.hospital_service.dto.request.HospitalRequest;
import com.hospital_service.dto.response.HospitalResponse;
import com.hospital_service.service.HospitalService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/hospitals")
@RequiredArgsConstructor
@Slf4j
public class HospitalController {

    private final HospitalService hospitalService;

    // 🏥 Naya hospital add karo
    @PostMapping
    public ResponseEntity<HospitalResponse> addHospital(
            @Valid @RequestBody HospitalRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(hospitalService.addHospital(request));
    }

    // 📋 Sab hospitals dekho
    @GetMapping
    public ResponseEntity<List<HospitalResponse>> getAllHospitals() {
        return ResponseEntity.ok(hospitalService.getAllHospitals());
    }

    // 🔍 ID se hospital dekho
    @GetMapping("/{id}")
    public ResponseEntity<HospitalResponse> getHospitalById(@PathVariable Long id) {
        return ResponseEntity.ok(hospitalService.getHospitalById(id));
    }

    // 🛏️ Available beds wale hospitals
    @GetMapping("/available-beds")
    public ResponseEntity<List<HospitalResponse>> getHospitalsWithAvailableBeds() {
        return ResponseEntity.ok(hospitalService.getHospitalsWithAvailableBeds());
    }

    // 🏥 Available ICU beds wale hospitals
    @GetMapping("/available-icu-beds")
    public ResponseEntity<List<HospitalResponse>> getHospitalsWithAvailableIcuBeds() {
        return ResponseEntity.ok(hospitalService.getHospitalsWithAvailableIcuBeds());
    }

    // 🔄 Bed availability update karo
    @PatchMapping("/{id}/beds")
    public ResponseEntity<HospitalResponse> updateBedAvailability(
            @PathVariable Long id,
            @Valid @RequestBody BedUpdateRequest request) {
        return ResponseEntity.ok(hospitalService.updateBedAvailability(id, request));
    }

    // 🗺️ Nearest hospital with beds
    @GetMapping("/nearest")
    public ResponseEntity<HospitalResponse> findNearestHospital(
            @RequestParam Double latitude,
            @RequestParam Double longitude) {
        return ResponseEntity.ok(hospitalService.findNearestHospitalWithBeds(latitude, longitude));
    }

    // ❌ Hospital delete karo
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHospital(@PathVariable Long id) {
        hospitalService.deleteHospital(id);
        return ResponseEntity.noContent().build();
    }
}