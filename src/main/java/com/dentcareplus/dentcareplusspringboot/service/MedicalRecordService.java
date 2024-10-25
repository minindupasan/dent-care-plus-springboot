package com.dentcareplus.dentcareplusspringboot.service;

import com.dentcareplus.dentcareplusspringboot.entity.MedicalRecord;
import com.dentcareplus.dentcareplusspringboot.entity.Patient;
import com.dentcareplus.dentcareplusspringboot.repository.MedicalRecordRepository;
import com.dentcareplus.dentcareplusspringboot.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicalRecordService {
    private final MedicalRecordRepository medicalRecordRepository;
    private final PatientRepository patientRepository;

    @Autowired
    public MedicalRecordService(MedicalRecordRepository medicalRecordRepository, PatientRepository patientRepository) {
        this.medicalRecordRepository = medicalRecordRepository;
        this.patientRepository = patientRepository;
    }

    // Create a new medical record for a valid patient ID
    public MedicalRecord createMedicalRecord(Long patientId, MedicalRecord medicalRecord) {
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new IllegalArgumentException("Patient with ID " + patientId + " not found."));

        medicalRecord.setPatient(patient);
        return medicalRecordRepository.save(medicalRecord);
    }

    // Get all medical records
    public List<MedicalRecord> getAllMedicalRecords() {
        List<MedicalRecord> medicalRecords = medicalRecordRepository.findAll();
        if (medicalRecords.isEmpty()) {
            throw new IllegalStateException("No medical records found.");
        }
        return medicalRecords;
    }

    // Get medical record by ID
    public MedicalRecord getMedicalRecordById(Long id) {
        return medicalRecordRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Medical record with ID " + id + " not found."));
    }

    // Update an existing medical record
    public MedicalRecord updateMedicalRecord(Long id, MedicalRecord medicalRecordDetails) {
        MedicalRecord medicalRecord = medicalRecordRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Medical record with ID " + id + " not found."));

        medicalRecord.setBloodType(medicalRecordDetails.getBloodType());
        medicalRecord.setDiabetes(medicalRecordDetails.getDiabetes());
        medicalRecord.setHypertension(medicalRecordDetails.getHypertension());
        medicalRecord.setHeartDisease(medicalRecordDetails.getHeartDisease());
        medicalRecord.setBleedingDisorders(medicalRecordDetails.getBleedingDisorders());
        medicalRecord.setOsteoporosis(medicalRecordDetails.getOsteoporosis());
        medicalRecord.setArthritis(medicalRecordDetails.getArthritis());
        medicalRecord.setAsthma(medicalRecordDetails.getAsthma());
        medicalRecord.setEpilepsy(medicalRecordDetails.getEpilepsy());
        medicalRecord.setHivAids(medicalRecordDetails.getHivAids());
        medicalRecord.setHepatitis(medicalRecordDetails.getHepatitis());
        medicalRecord.setThyroidDisorder(medicalRecordDetails.getThyroidDisorder());
        medicalRecord.setPregnancy(medicalRecordDetails.getPregnancy());
        medicalRecord.setSurgeries(medicalRecordDetails.getSurgeries());
        medicalRecord.setCurrentMedications(medicalRecordDetails.getCurrentMedications());
        medicalRecord.setDrugAllergies(medicalRecordDetails.getDrugAllergies());
        medicalRecord.setAllergies(medicalRecordDetails.getAllergies());
        medicalRecord.setMedications(medicalRecordDetails.getMedications());
        medicalRecord.setMedicalConditions(medicalRecordDetails.getMedicalConditions());
        medicalRecord.setEmergencyContactName(medicalRecordDetails.getEmergencyContactName());
        medicalRecord.setEmergencyContactNumber(medicalRecordDetails.getEmergencyContactNumber());

        return medicalRecordRepository.save(medicalRecord);
    }

    // Delete a medical record by ID
    public boolean deleteMedicalRecord(Long id) {
        MedicalRecord medicalRecord = medicalRecordRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Medical record with ID " + id + " not found."));
        medicalRecordRepository.delete(medicalRecord);
        return true;
    }
}