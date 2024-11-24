package com.dentcareplus.dentcareplusspringboot.repository;

import com.dentcareplus.dentcareplusspringboot.entity.MedicalRecord;
import com.dentcareplus.dentcareplusspringboot.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MedicalRecordRepository extends JpaRepository<MedicalRecord, Long> {
    Optional<MedicalRecord> findByPatient(Patient patient);
}