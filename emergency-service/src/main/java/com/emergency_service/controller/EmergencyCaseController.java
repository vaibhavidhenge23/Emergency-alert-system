package com.emergency_service.controller;

import com.emergency_service.dto.request.EmergencyRequest;
import com.emergency_service.dto.response.EmergencyResponse;
import com.emergency_service.model.enums.EmergencyStatus;
import com.emergency_service.service.EmergencyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/emergency")
@RequiredArgsConstructor
@Slf4j
public class EmergencyCaseController {

    private final EmergencyService emergencyService;

    // 🚨 SOS — Naya emergency case create karo
    @PostMapping("/sos")
    public ResponseEntity<EmergencyResponse> createEmergency(
            @Valid @RequestBody EmergencyRequest request) {
        log.info("SOS received from: {}", request.getCallerPhone());
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(emergencyService.createEmergency(request));
    }

    // 📋 Sab cases dekho
    @GetMapping
    public ResponseEntity<List<EmergencyResponse>> getAllEmergencies() {
        return ResponseEntity.ok(emergencyService.getAllEmergencies());
    }

    // 🔍 ID se case dekho
    @GetMapping("/{id}")
    public ResponseEntity<EmergencyResponse> getEmergencyById(@PathVariable Long id) {
        return ResponseEntity.ok(emergencyService.getEmergencyById(id));
    }

    // 📊 Status se cases filter karo
    @GetMapping("/status/{status}")
    public ResponseEntity<List<EmergencyResponse>> getByStatus(
            @PathVariable EmergencyStatus status) {
        return ResponseEntity.ok(emergencyService.getEmergenciesByStatus(status));
    }

    // 🔄 Status update karo
    @PatchMapping("/{id}/status")
    public ResponseEntity<EmergencyResponse> updateStatus(
            @PathVariable Long id,
            @RequestParam EmergencyStatus status) {
        return ResponseEntity.ok(emergencyService.updateStatus(id, status));
    }

    // 🚑 Ambulance assign karo
    @PatchMapping("/{id}/assign-ambulance")
    public ResponseEntity<EmergencyResponse> assignAmbulance(
            @PathVariable Long id,
            @RequestParam Long ambulanceId) {
        return ResponseEntity.ok(emergencyService.assignAmbulance(id, ambulanceId));
    }

    // 🏥 Hospital assign karo
    @PatchMapping("/{id}/assign-hospital")
    public ResponseEntity<EmergencyResponse> assignHospital(
            @PathVariable Long id,
            @RequestParam Long hospitalId) {
        return ResponseEntity.ok(emergencyService.assignHospital(id, hospitalId));
    }

    // ❌ Case delete karo
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmergency(@PathVariable Long id) {
        emergencyService.deleteEmergency(id);
        return ResponseEntity.noContent().build();
    }
}