package com.dentcareplus.dentcareplusspringboot.service;

import com.dentcareplus.dentcareplusspringboot.dto.PatientDTO;
import com.dentcareplus.dentcareplusspringboot.entity.Patient;
import com.dentcareplus.dentcareplusspringboot.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PatientService {
    private final PatientRepository patientRepository;

    @Autowired
    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    // Create a new patient
    public PatientDTO createPatient(Patient patient) {
        if (patient.getCreatedDate() == null) {
            patient.setCreatedDate(java.time.LocalDate.now());
        }
        Patient savedPatient = patientRepository.save(patient);
        return mapToPatientDTO(savedPatient);
    }

    // Get all patients
    public List<PatientDTO> getAllPatients() {
        return patientRepository.findAll().stream()
                .map(this::mapToPatientDTO)
                .collect(Collectors.toList());
    }

    // Get a patient by ID
    public PatientDTO getPatientById(Long id) {
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Patient with ID " + id + " not found."));
        return mapToPatientDTO(patient);
    }

    // Update a patient's details
    public PatientDTO updatePatient(Long id, Patient patientDetails) {
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Patient with ID " + id + " not found."));
        patient.setFirstName(patientDetails.getFirstName());
        patient.setLastName(patientDetails.getLastName());
        patient.setEmail(patientDetails.getEmail());
        patient.setContactNo(patientDetails.getContactNo());
        patient.setGender(patientDetails.getGender());
        patient.setDob(patientDetails.getDob());
        patient.setCreatedDate(patientDetails.getCreatedDate());

        Patient updatedPatient = patientRepository.save(patient);
        return mapToPatientDTO(updatedPatient);
    }

    // Delete a patient by ID
    public void deletePatient(Long id) {
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Patient with ID " + id + " not found."));
        patientRepository.delete(patient);
    }

    // Helper method to map Patient to PatientDTO
    private PatientDTO mapToPatientDTO(Patient patient) {
        return new PatientDTO(
                patient.getPatientID(),
                patient.getFirstName(),
                patient.getLastName(),
                patient.getEmail(),
                patient.getContactNo(),
                patient.getGender(),
                patient.getDob(),
                patient.getCreatedDate()
        );
    }
}