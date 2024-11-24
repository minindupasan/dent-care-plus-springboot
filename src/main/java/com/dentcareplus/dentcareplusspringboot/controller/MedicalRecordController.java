package com.dentcareplus.dentcareplusspringboot.controller;

import com.dentcareplus.dentcareplusspringboot.dto.MedicalRecordDTO;
import com.dentcareplus.dentcareplusspringboot.service.MedicalRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/medical-records")
@CrossOrigin(origins = {"https://dental-clinic-management-system-five.vercel.app", "http://localhost:3000"})
public class MedicalRecordController {

    private final MedicalRecordService medicalRecordService;

    @Autowired
    public MedicalRecordController(MedicalRecordService medicalRecordService) {
        this.medicalRecordService = medicalRecordService;
    }

    // POST request to create or update a medical record
    @PostMapping("/create/{patientId}")
    public ResponseEntity<MedicalRecordDTO> createOrUpdateMedicalRecord(@PathVariable Long patientId, @RequestBody MedicalRecordDTO medicalRecordDTO) {
        MedicalRecordDTO savedMedicalRecord = medicalRecordService.createOrUpdateMedicalRecord(patientId, medicalRecordDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedMedicalRecord);
    }

    // GET request to retrieve all medical records
    @GetMapping
    public ResponseEntity<List<MedicalRecordDTO>> getAllMedicalRecords() {
        List<MedicalRecordDTO> medicalRecords = medicalRecordService.getAllMedicalRecords();
        if (medicalRecords.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.ok(medicalRecords);
    }

    // GET request to retrieve a medical record by patient ID
    @GetMapping("/{patientId}")
    public ResponseEntity<MedicalRecordDTO> getMedicalRecordByPatientId(@PathVariable Long patientId) {
        MedicalRecordDTO medicalRecord = medicalRecordService.getMedicalRecordByPatientId(patientId);
        return ResponseEntity.ok(medicalRecord);
    }

    // PUT request to update a medical record
    @PutMapping("/update/{recordId}")
    public ResponseEntity<MedicalRecordDTO> updateMedicalRecord(@PathVariable Long recordId, @RequestBody MedicalRecordDTO medicalRecordDetails) {
        MedicalRecordDTO updatedMedicalRecord = medicalRecordService.updateMedicalRecord(recordId, medicalRecordDetails);
        return ResponseEntity.ok(updatedMedicalRecord);
    }

    // DELETE request to delete a medical record
    @DeleteMapping("/delete/{recordId}")
    public ResponseEntity<Void> deleteMedicalRecord(@PathVariable Long recordId) {
        medicalRecordService.deleteMedicalRecord(recordId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}