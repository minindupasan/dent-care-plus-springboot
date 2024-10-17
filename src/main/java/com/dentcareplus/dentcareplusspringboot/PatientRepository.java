package com.dentcareplus.dentcareplusspringboot;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface PatientRepository extends JpaRepository<Patient, Long> {

}
