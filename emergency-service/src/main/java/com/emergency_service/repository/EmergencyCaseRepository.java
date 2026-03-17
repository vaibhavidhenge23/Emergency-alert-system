package com.emergency_service.repository;


import com.emergency_service.model.EmergencyCase;
import com.emergency_service.model.enums.EmergencyStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmergencyCaseRepository extends JpaRepository<EmergencyCase, Long> {

    List<EmergencyCase> findByStatus(EmergencyStatus status);

    List<EmergencyCase> findByCallerPhone(String callerPhone);

    List<EmergencyCase> findByAssignedAmbulanceId(Long ambulance);

    List<EmergencyCase> findByAssignedHospitalId(Long hospitalId);




}
