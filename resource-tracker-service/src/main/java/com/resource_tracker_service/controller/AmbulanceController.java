package com.resource_tracker_service.controller;

import com.resource_tracker_service.dto.request.AmbulanceRequest;
import com.resource_tracker_service.dto.request.LocationUpdateRequest;
import com.resource_tracker_service.dto.response.AmbulanceResponse;
import com.resource_tracker_service.model.enums.AmbulanceStatus;
import com.resource_tracker_service.service.AmbulanceService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/resources")
@RequiredArgsConstructor
@Slf4j
public class AmbulanceController {

    private final AmbulanceService ambulanceService;

    @PostMapping
    public ResponseEntity<AmbulanceResponse> registerAmbulance(
            @Valid @RequestBody AmbulanceRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ambulanceService.registerAmbulance(request));
    }

    @GetMapping
    public ResponseEntity<List<AmbulanceResponse>> getAllAmbulances() {
        return ResponseEntity.ok(ambulanceService.getAllAmbulances());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AmbulanceResponse> getAmbulanceById(@PathVariable Long id) {
        return ResponseEntity.ok(ambulanceService.getAmbulanceById(id));
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<AmbulanceResponse>> getByStatus(
            @PathVariable AmbulanceStatus status) {
        return ResponseEntity.ok(ambulanceService.getAmbulancesByStatus(status));
    }

    @PatchMapping("/{id}/location")
    public ResponseEntity<AmbulanceResponse> updateLocation(
            @PathVariable Long id,
            @Valid @RequestBody LocationUpdateRequest request) {
        return ResponseEntity.ok(ambulanceService.updateLocation(id, request));
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<AmbulanceResponse> updateStatus(
            @PathVariable Long id,
            @RequestParam AmbulanceStatus status) {
        return ResponseEntity.ok(ambulanceService.updateStatus(id, status));
    }

    @GetMapping("/nearest")
    public ResponseEntity<AmbulanceResponse> findNearest(
            @RequestParam Double latitude,
            @RequestParam Double longitude) {
        return ResponseEntity.ok(ambulanceService.findNearestAvailableAmbulance(latitude, longitude));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAmbulance(@PathVariable Long id) {
        ambulanceService.deleteAmbulance(id);
        return ResponseEntity.noContent().build();
    }
}