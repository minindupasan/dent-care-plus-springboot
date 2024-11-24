package com.dentcareplus.dentcareplusspringboot.controller;

import com.dentcareplus.dentcareplusspringboot.dto.PrescriptionDTO;
import com.dentcareplus.dentcareplusspringboot.service.PrescriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/prescriptions")
@CrossOrigin(origins = "http://localhost:3000")
public class PrescriptionController {

    @Autowired
    private PrescriptionService prescriptionService;

    // Create a prescription linked to an appointment ID
    @PostMapping("/create/{appointmentId}")
    public ResponseEntity<PrescriptionDTO> createPrescription(
            @PathVariable Long appointmentId,
            @RequestBody PrescriptionDTO prescriptionDTO) {
        PrescriptionDTO createdPrescription = prescriptionService.createPrescriptionByAppointmentId(appointmentId, prescriptionDTO);
        return ResponseEntity.ok(createdPrescription);
    }

    // Get all prescriptions
    @GetMapping
    public ResponseEntity<List<PrescriptionDTO>> getAllPrescriptions() {
        return ResponseEntity.ok(prescriptionService.getAllPrescriptions());
    }

    // Get a prescription by ID
    @GetMapping("/{id}")
    public ResponseEntity<PrescriptionDTO> getPrescriptionById(@PathVariable Long id) {
        return prescriptionService.getPrescriptionById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Update a prescription
    @PutMapping("/update/{id}")
    public ResponseEntity<PrescriptionDTO> updatePrescription(
            @PathVariable Long id,
            @RequestBody PrescriptionDTO updatedPrescriptionDTO) {
        try {
            PrescriptionDTO updated = prescriptionService.updatePrescription(id, updatedPrescriptionDTO);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Delete a prescription
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePrescription(@PathVariable Long id) {
        prescriptionService.deletePrescription(id);
        return ResponseEntity.noContent().build();
    }
}