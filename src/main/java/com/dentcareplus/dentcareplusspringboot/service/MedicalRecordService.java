package com.dentcareplus.dentcareplusspringboot.service;

import com.dentcareplus.dentcareplusspringboot.dto.MedicalRecordDTO;
import com.dentcareplus.dentcareplusspringboot.entity.MedicalRecord;
import com.dentcareplus.dentcareplusspringboot.entity.Patient;
import com.dentcareplus.dentcareplusspringboot.repository.MedicalRecordRepository;
import com.dentcareplus.dentcareplusspringboot.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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
    public MedicalRecordDTO createOrUpdateMedicalRecord(Long patientId, MedicalRecordDTO medicalRecordDTO) {
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new IllegalArgumentException("Patient with ID " + patientId + " not found."));

        MedicalRecord medicalRecord = new MedicalRecord();
        medicalRecord.setPatient(patient);
        medicalRecord.setBloodType(medicalRecordDTO.getBloodType());
        medicalRecord.setDiabetes(medicalRecordDTO.getDiabetes());
        medicalRecord.setHypertension(medicalRecordDTO.getHypertension());
        medicalRecord.setHeartDisease(medicalRecordDTO.getHeartDisease());
        medicalRecord.setBleedingDisorders(medicalRecordDTO.getBleedingDisorders());
        medicalRecord.setOsteoporosis(medicalRecordDTO.getOsteoporosis());
        medicalRecord.setArthritis(medicalRecordDTO.getArthritis());
        medicalRecord.setAsthma(medicalRecordDTO.getAsthma());
        medicalRecord.setEpilepsy(medicalRecordDTO.getEpilepsy());
        medicalRecord.setHivAids(medicalRecordDTO.getHivAids());
        medicalRecord.setHepatitis(medicalRecordDTO.getHepatitis());
        medicalRecord.setThyroidDisorder(medicalRecordDTO.getThyroidDisorder());
        medicalRecord.setPregnancy(medicalRecordDTO.getPregnancy());
        medicalRecord.setSurgeries(medicalRecordDTO.getSurgeries());
        medicalRecord.setCurrentMedications(medicalRecordDTO.getCurrentMedications());
        medicalRecord.setDrugAllergies(medicalRecordDTO.getDrugAllergies());
        medicalRecord.setAllergies(medicalRecordDTO.getAllergies());
        medicalRecord.setMedications(medicalRecordDTO.getMedications());
        medicalRecord.setMedicalConditions(medicalRecordDTO.getMedicalConditions());
        medicalRecord.setEmergencyContactName(medicalRecordDTO.getEmergencyContactName());
        medicalRecord.setEmergencyContactNumber(medicalRecordDTO.getEmergencyContactNumber());

        // Save the record and return the DTO
        medicalRecord = medicalRecordRepository.save(medicalRecord);
        return mapToDTO(medicalRecord);
    }

    // Get all medical records and map to DTO
    public List<MedicalRecordDTO> getAllMedicalRecords() {
        return medicalRecordRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    // Get medical record by patient ID and map to DTO
    public MedicalRecordDTO getMedicalRecordByPatientId(Long patientId) {
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new IllegalArgumentException("Patient with ID " + patientId + " not found."));

        MedicalRecord medicalRecord = medicalRecordRepository.findByPatient(patient)
                .orElseThrow(() -> new IllegalArgumentException("Medical record for patient with ID " + patientId + " not found."));

        return mapToDTO(medicalRecord);
    }

    // Update a medical record by record ID
    public MedicalRecordDTO updateMedicalRecord(Long recordId, MedicalRecordDTO medicalRecordDTO) {
        MedicalRecord medicalRecord = medicalRecordRepository.findById(recordId)
                .orElseThrow(() -> new IllegalArgumentException("Medical record with ID " + recordId + " not found."));

        // Update all fields from DTO
        medicalRecord.setBloodType(medicalRecordDTO.getBloodType());
        medicalRecord.setDiabetes(medicalRecordDTO.getDiabetes());
        medicalRecord.setHypertension(medicalRecordDTO.getHypertension());
        medicalRecord.setHeartDisease(medicalRecordDTO.getHeartDisease());
        medicalRecord.setBleedingDisorders(medicalRecordDTO.getBleedingDisorders());
        medicalRecord.setOsteoporosis(medicalRecordDTO.getOsteoporosis());
        medicalRecord.setArthritis(medicalRecordDTO.getArthritis());
        medicalRecord.setAsthma(medicalRecordDTO.getAsthma());
        medicalRecord.setEpilepsy(medicalRecordDTO.getEpilepsy());
        medicalRecord.setHivAids(medicalRecordDTO.getHivAids());
        medicalRecord.setHepatitis(medicalRecordDTO.getHepatitis());
        medicalRecord.setThyroidDisorder(medicalRecordDTO.getThyroidDisorder());
        medicalRecord.setPregnancy(medicalRecordDTO.getPregnancy());
        medicalRecord.setSurgeries(medicalRecordDTO.getSurgeries());
        medicalRecord.setCurrentMedications(medicalRecordDTO.getCurrentMedications());
        medicalRecord.setDrugAllergies(medicalRecordDTO.getDrugAllergies());
        medicalRecord.setAllergies(medicalRecordDTO.getAllergies());
        medicalRecord.setMedications(medicalRecordDTO.getMedications());
        medicalRecord.setMedicalConditions(medicalRecordDTO.getMedicalConditions());
        medicalRecord.setEmergencyContactName(medicalRecordDTO.getEmergencyContactName());
        medicalRecord.setEmergencyContactNumber(medicalRecordDTO.getEmergencyContactNumber());

        // Save and return updated MedicalRecordDTO
        medicalRecord = medicalRecordRepository.save(medicalRecord);
        return mapToDTO(medicalRecord);
    }

    // Delete a medical record by ID
    public void deleteMedicalRecord(Long recordId) {
        MedicalRecord medicalRecord = medicalRecordRepository.findById(recordId)
                .orElseThrow(() -> new IllegalArgumentException("Medical record with ID " + recordId + " not found."));
        medicalRecordRepository.delete(medicalRecord);
    }

    // Helper method to map MedicalRecord entity to MedicalRecordDTO
    private MedicalRecordDTO mapToDTO(MedicalRecord medicalRecord) {
        MedicalRecordDTO dto = new MedicalRecordDTO();
        dto.setRecordID(medicalRecord.getRecordID());
        dto.setPatientId(medicalRecord.getPatient().getPatientID());
        dto.setBloodType(medicalRecord.getBloodType());
        dto.setDiabetes(medicalRecord.getDiabetes());
        dto.setHypertension(medicalRecord.getHypertension());
        dto.setHeartDisease(medicalRecord.getHeartDisease());
        dto.setBleedingDisorders(medicalRecord.getBleedingDisorders());
        dto.setOsteoporosis(medicalRecord.getOsteoporosis());
        dto.setArthritis(medicalRecord.getArthritis());
        dto.setAsthma(medicalRecord.getAsthma());
        dto.setEpilepsy(medicalRecord.getEpilepsy());
        dto.setHivAids(medicalRecord.getHivAids());
        dto.setHepatitis(medicalRecord.getHepatitis());
        dto.setThyroidDisorder(medicalRecord.getThyroidDisorder());
        dto.setPregnancy(medicalRecord.getPregnancy());
        dto.setSurgeries(medicalRecord.getSurgeries());
        dto.setCurrentMedications(medicalRecord.getCurrentMedications());
        dto.setDrugAllergies(medicalRecord.getDrugAllergies());
        dto.setAllergies(medicalRecord.getAllergies());
        dto.setMedications(medicalRecord.getMedications());
        dto.setMedicalConditions(medicalRecord.getMedicalConditions());
        dto.setEmergencyContactName(medicalRecord.getEmergencyContactName());
        dto.setEmergencyContactNumber(medicalRecord.getEmergencyContactNumber());
        return dto;
    }
}