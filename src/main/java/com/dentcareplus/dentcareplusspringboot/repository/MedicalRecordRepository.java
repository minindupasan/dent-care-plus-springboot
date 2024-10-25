package com.dentcareplus.dentcareplusspringboot.repository;

import com.dentcareplus.dentcareplusspringboot.entity.MedicalRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicalRecordRepository extends JpaRepository<MedicalRecord, Long> {
}