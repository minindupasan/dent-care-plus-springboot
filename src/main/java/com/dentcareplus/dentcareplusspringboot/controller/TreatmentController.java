package com.dentcareplus.dentcareplusspringboot.controller;

import com.dentcareplus.dentcareplusspringboot.entity.Treatment;
import com.dentcareplus.dentcareplusspringboot.service.TreatmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/treatments")
@CrossOrigin(origins = {"https://dental-clinic-management-system-five.vercel.app", "http://localhost:3000"})
public class TreatmentController {

    @Autowired
    private TreatmentService treatmentService;

    // GET request to fetch all treatments
    @GetMapping
    public ResponseEntity<List<Treatment>> getAllTreatments() {
        List<Treatment> treatments = treatmentService.getAllTreatments();

        if (treatments.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }

        return ResponseEntity.ok(treatments);
    }

    // GET request to fetch a treatment by ID
    @GetMapping("/{treatmentId}")
    public ResponseEntity<Treatment> getTreatmentById(@PathVariable Long treatmentId) {
        Treatment treatment = treatmentService.getTreatmentById(treatmentId);

        if (treatment == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.ok(treatment);
    }

    // PUT request to update treatment details
    @PutMapping("/update/{treatmentId}")
    public ResponseEntity<Treatment> updateTreatment(@PathVariable Long treatmentId, @RequestBody Treatment treatmentDetails) {
        Treatment updatedTreatment = treatmentService.updateTreatment(treatmentId, treatmentDetails);

        if (updatedTreatment == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.ok(updatedTreatment);
    }

    // DELETE request to delete a treatment by ID
    @DeleteMapping("/delete/{treatmentId}")
    public ResponseEntity<Void> deleteTreatment(@PathVariable Long treatmentId) {
        boolean isDeleted = treatmentService.deleteTreatment(treatmentId);

        if (isDeleted) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}