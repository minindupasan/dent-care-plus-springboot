package com.dentcareplus.dentcareplusspringboot.service;

import com.dentcareplus.dentcareplusspringboot.entity.Patient;
import com.dentcareplus.dentcareplusspringboot.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatientService {
    private final PatientRepository patientRepository;

    @Autowired
    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    // Create a new patient
    public Patient createPatient(Patient patient) {
        // Set the created date to the current date if not provided
        if (patient.getCreatedDate() == null) {
            patient.setCreatedDate(java.time.LocalDate.now());
        }
        return patientRepository.save(patient);
    }

    // Get all patients with formatted patient ID
    public List<Patient> getAllPatients() {
        List<Patient> patients = patientRepository.findAll();
        patients.forEach(patient -> {
            System.out.println("Patient ID: " + patient.getPatientID()); // Log the formatted patient ID
        });
        return patients;
    }

    // Get a patient by ID with formatted patient ID
    public Patient getPatientById(Long id) {
        Optional<Patient> patient = patientRepository.findById(id);
        if (patient.isPresent()) {
            Patient foundPatient = patient.get();
            System.out.println("Retrieved Patient ID: " + foundPatient.getPatientID()); // Log formatted ID
            return foundPatient;
        }
        return null;
    }

    // Update a patient's details and log the formatted patient ID
    public Patient updatePatient(Long id, Patient patientDetails) {
        Optional<Patient> optionalPatient = patientRepository.findById(id);
        if (optionalPatient.isPresent()) {
            Patient patient = optionalPatient.get();
            patient.setFirstName(patientDetails.getFirstName());
            patient.setLastName(patientDetails.getLastName());
            patient.setEmail(patientDetails.getEmail());
            patient.setGender(patientDetails.getGender());
            patient.setDob(patientDetails.getDob());
            patient.setMedicalRecords(patientDetails.getMedicalRecords());
            patient.setCreatedDate(patientDetails.getCreatedDate());
            Patient updatedPatient = patientRepository.save(patient);
            System.out.println("Updated Patient ID: " + updatedPatient.getPatientID()); // Log formatted ID
            return updatedPatient;
        } else {
            return null;
        }
    }

    // Delete a patient by ID
    public void deletePatient(Long id) {
        Optional<Patient> patient = patientRepository.findById(id);
        if (patient.isPresent()) {
            System.out.println("Deleting Patient ID: " + patient.get().getPatientID()); // Log formatted ID before deletion
            patientRepository.deleteById(id);
        }
    }
}
