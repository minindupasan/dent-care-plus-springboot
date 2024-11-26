package com.dentcareplus.dentcareplusspringboot.controller;

import com.dentcareplus.dentcareplusspringboot.dto.PatientDTO;
import com.dentcareplus.dentcareplusspringboot.entity.Patient;
import com.dentcareplus.dentcareplusspringboot.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/patients")
@CrossOrigin
public class PatientController {

    @Autowired
    private PatientService patientService;

    @PostMapping("/create")
    @PreAuthorize("hasRole('DENTIST') or hasRole('RECEPTIONIST')")
    public ResponseEntity<PatientDTO> createPatient(@RequestBody Patient patient) {
        PatientDTO newPatient = patientService.createPatient(patient);
        return ResponseEntity.ok(newPatient);
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('DENTIST', 'RECEPTIONIST', 'ASSISTANT')")
    public ResponseEntity<List<PatientDTO>> getAllPatients() {
        List<PatientDTO> patients = patientService.getAllPatients();
        return ResponseEntity.ok(patients);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('DENTIST', 'RECEPTIONIST')")
    public ResponseEntity<PatientDTO> getPatientById(@PathVariable Long id) {
        PatientDTO patient = patientService.getPatientById(id);
        return ResponseEntity.ok(patient);
    }

    @PutMapping("/update/{id}")
    @PreAuthorize("hasAnyRole('DENTIST', 'RECEPTIONIST')")
    public ResponseEntity<PatientDTO> updatePatient(@PathVariable Long id, @RequestBody Patient patientDetails) {
        PatientDTO updatedPatient = patientService.updatePatient(id, patientDetails);
        return ResponseEntity.ok(updatedPatient);
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAnyRole('DENTIST', 'RECEPTIONIST')")
    public ResponseEntity<Void> deletePatient(@PathVariable Long id) {
        patientService.deletePatient(id);
        return ResponseEntity.noContent().build();
    }
}