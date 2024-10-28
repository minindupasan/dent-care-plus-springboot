package com.dentcareplus.dentcareplusspringboot.controller;

import com.dentcareplus.dentcareplusspringboot.entity.MedicalRecord;
import com.dentcareplus.dentcareplusspringboot.entity.Patient;
import com.dentcareplus.dentcareplusspringboot.service.MedicalRecordService;
import com.dentcareplus.dentcareplusspringboot.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/medical-records")
@CrossOrigin(origins = "https://dental-clinic-management-system-five.vercel.app")
public class MedicalRecordController {

    @Autowired
    private MedicalRecordService medicalRecordService;

    @Autowired
    private PatientService patientService;

    // POST request to create a medical record
    @PostMapping("/create/{patientId}")
    public ResponseEntity<MedicalRecord> createMedicalRecord(@PathVariable Long patientId, @RequestBody MedicalRecord medicalRecord) {
        Patient patient = patientService.getPatientById(patientId);

        if (patient == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        MedicalRecord newMedicalRecord = medicalRecordService.createMedicalRecord(patientId, medicalRecord);
        return ResponseEntity.status(HttpStatus.CREATED).body(newMedicalRecord);
    }

    // GET request to get all medical records
    @GetMapping
    public ResponseEntity<List<MedicalRecord>> getAllMedicalRecords() {
        List<MedicalRecord> medicalRecords = medicalRecordService.getAllMedicalRecords();

        if (medicalRecords.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }

        return ResponseEntity.ok(medicalRecords);
    }

    // GET request to get a specific medical record by ID
    @GetMapping("/{recordId}")
    public ResponseEntity<MedicalRecord> getMedicalRecordById(@PathVariable Long recordId) {
        MedicalRecord medicalRecord = medicalRecordService.getMedicalRecordById(recordId);

        if (medicalRecord == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.ok(medicalRecord);
    }

    // PUT request to update a medical record
    @PutMapping("/update/{recordId}")
    public ResponseEntity<MedicalRecord> updateMedicalRecord(@PathVariable Long recordId, @RequestBody MedicalRecord medicalRecordDetails) {
        MedicalRecord updatedMedicalRecord = medicalRecordService.updateMedicalRecord(recordId, medicalRecordDetails);

        if (updatedMedicalRecord == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.ok(updatedMedicalRecord);
    }

    // DELETE request to delete a medical record
    @DeleteMapping("/delete/{recordId}")
    public ResponseEntity<Void> deleteMedicalRecord(@PathVariable Long recordId) {
        MedicalRecord medicalRecord = medicalRecordService.getMedicalRecordById(recordId);

        if (medicalRecord == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        boolean isDeleted = medicalRecordService.deleteMedicalRecord(recordId);
        if (isDeleted) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
}