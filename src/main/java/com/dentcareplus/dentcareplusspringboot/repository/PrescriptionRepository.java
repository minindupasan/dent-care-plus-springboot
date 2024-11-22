package com.dentcareplus.dentcareplusspringboot.repository;

import com.dentcareplus.dentcareplusspringboot.entity.Prescription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrescriptionRepository extends JpaRepository<Prescription, Long> {
}