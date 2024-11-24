package com.dentcareplus.dentcareplusspringboot.controller;

import com.dentcareplus.dentcareplusspringboot.dto.PatientDTO;
import com.dentcareplus.dentcareplusspringboot.entity.Patient;
import com.dentcareplus.dentcareplusspringboot.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/patients")
@CrossOrigin(origins = {"https://dental-clinic-management-system-five.vercel.app", "http://localhost:3000"})
public class PatientController {
    @Autowired
    private PatientService patientService;

    @PostMapping("/create")
    public ResponseEntity<PatientDTO> createPatient(@RequestBody Patient patient) {
        PatientDTO newPatient = patientService.createPatient(patient);
        return ResponseEntity.ok(newPatient);
    }

    @GetMapping
    public ResponseEntity<List<PatientDTO>> getAllPatients() {
        List<PatientDTO> patients = patientService.getAllPatients();
        return ResponseEntity.ok(patients);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PatientDTO> getPatientById(@PathVariable Long id) {
        PatientDTO patient = patientService.getPatientById(id);
        return ResponseEntity.ok(patient);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<PatientDTO> updatePatient(@PathVariable Long id, @RequestBody Patient patientDetails) {
        PatientDTO updatedPatient = patientService.updatePatient(id, patientDetails);
        return ResponseEntity.ok(updatedPatient);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deletePatient(@PathVariable Long id) {
        patientService.deletePatient(id);
        return ResponseEntity.noContent().build();
    }
}