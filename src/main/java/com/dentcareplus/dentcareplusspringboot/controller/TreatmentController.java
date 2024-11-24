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

    @GetMapping
    public ResponseEntity<List<Treatment>> getAllTreatments() {
        List<Treatment> treatments = treatmentService.getAllTreatments();
        return ResponseEntity.ok(treatments);
    }

    @GetMapping("/{treatmentId}")
    public ResponseEntity<Treatment> getTreatmentById(@PathVariable Long treatmentId) {
        Treatment treatment = treatmentService.getTreatmentById(treatmentId);
        return ResponseEntity.ok(treatment);
    }

    @PutMapping("/update/{treatmentId}")
    public ResponseEntity<Treatment> updateTreatment(@PathVariable Long treatmentId, @RequestBody Treatment treatmentDetails) {
        Treatment updatedTreatment = treatmentService.updateTreatment(treatmentId, treatmentDetails);
        return ResponseEntity.ok(updatedTreatment);
    }

    @DeleteMapping("/delete/{treatmentId}")
    public ResponseEntity<Void> deleteTreatment(@PathVariable Long treatmentId) {
        treatmentService.deleteTreatment(treatmentId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}