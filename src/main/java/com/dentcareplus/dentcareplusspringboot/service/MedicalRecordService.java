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

    // Create or update a medical record for a valid patient ID
    public MedicalRecord createOrUpdateMedicalRecord(Long patientId, MedicalRecord medicalRecord) {
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new IllegalArgumentException("Patient with ID " + patientId + " not found."));
        medicalRecord.setPatient(patient);

        return medicalRecordRepository.save(medicalRecord);
    }

    // Get all medical records
    public List<MedicalRecord> getAllMedicalRecords() {
        return medicalRecordRepository.findAll();
    }

    // Get medical record by patient ID
    public MedicalRecord getMedicalRecordByPatientId(Long patientId) {
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new IllegalArgumentException("Patient with ID " + patientId + " not found."));
        return medicalRecordRepository.findByPatient(patient)
                .orElseThrow(() -> new IllegalArgumentException("Medical record for patient with ID " + patientId + " not found."));
    }

    // Update a medical record
    public MedicalRecord updateMedicalRecord(Long recordId, MedicalRecord medicalRecordDetails) {
        MedicalRecord medicalRecord = medicalRecordRepository.findById(recordId)
                .orElseThrow(() -> new IllegalArgumentException("Medical record with ID " + recordId + " not found."));

        // Update all fields
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
    public void deleteMedicalRecord(Long recordId) {
        MedicalRecord medicalRecord = medicalRecordRepository.findById(recordId)
                .orElseThrow(() -> new IllegalArgumentException("Medical record with ID " + recordId + " not found."));
        medicalRecordRepository.delete(medicalRecord);
    }
}