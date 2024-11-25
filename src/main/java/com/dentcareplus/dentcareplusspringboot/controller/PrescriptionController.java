package com.dentcareplus.dentcareplusspringboot.controller;

import com.dentcareplus.dentcareplusspringboot.entity.Prescription;
import com.dentcareplus.dentcareplusspringboot.service.PrescriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/prescription")
@CrossOrigin
public class PrescriptionController {

    @Autowired
    private PrescriptionService prescriptionService;

    // Create a prescription linked to an appointment ID
    @PostMapping("/create/{appointmentId}")
    public ResponseEntity<Prescription> createPrescription(
            @PathVariable Long appointmentId,
            @RequestBody Prescription prescription) {
        Prescription createdPrescription = prescriptionService.createPrescriptionByAppointmentId(appointmentId, prescription);
        return ResponseEntity.ok(createdPrescription);
    }

    // Get all prescriptions
    @GetMapping
    public ResponseEntity<List<Prescription>> getAllPrescriptions() {
        return ResponseEntity.ok(prescriptionService.getAllPrescriptions());
    }

    // Get a prescription by ID
    @GetMapping("/{id}")
    public ResponseEntity<Prescription> getPrescriptionById(@PathVariable Long id) {
        return prescriptionService.getPrescriptionById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Update a prescription
    @PutMapping("/{id}")
    public ResponseEntity<Prescription> updatePrescription(
            @PathVariable Long id,
            @RequestBody Prescription updatedPrescription) {
        try {
            Prescription updated = prescriptionService.updatePrescription(id, updatedPrescription);
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