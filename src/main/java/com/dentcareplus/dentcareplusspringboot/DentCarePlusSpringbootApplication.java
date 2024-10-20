package com.dentcareplus.dentcareplusspringboot;

import com.dentcareplus.dentcareplusspringboot.entity.Patient;
import com.dentcareplus.dentcareplusspringboot.repository.PatientRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class DentCarePlusSpringbootApplication {
    private final PatientRepository patientRepository;

    public DentCarePlusSpringbootApplication(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(DentCarePlusSpringbootApplication.class, args);
    }
}