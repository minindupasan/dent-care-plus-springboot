package com.dentcareplus.dentcareplusspringboot.controller;

import com.dentcareplus.dentcareplusspringboot.dto.TreatmentDTO;
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
    public ResponseEntity<List<TreatmentDTO>> getAllTreatments() {
        List<TreatmentDTO> treatments = treatmentService.getAllTreatments();

        if (treatments.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }

        return ResponseEntity.ok(treatments);
    }

    // GET request to fetch a treatment by ID
    @GetMapping("/{treatmentId}")
    public ResponseEntity<TreatmentDTO> getTreatmentById(@PathVariable Long treatmentId) {
        TreatmentDTO treatmentDTO = treatmentService.getTreatmentById(treatmentId);

        if (treatmentDTO == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.ok(treatmentDTO);
    }

    // PUT request to update a treatment by ID
    @PutMapping("/update/{treatmentId}")
    public ResponseEntity<TreatmentDTO> updateTreatment(
            @PathVariable Long treatmentId,
            @RequestBody TreatmentDTO treatmentDTO) {
        TreatmentDTO updatedTreatment = treatmentService.updateTreatment(treatmentId, treatmentDTO);

        if (updatedTreatment == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.ok(updatedTreatment);
    }

    // DELETE request to delete a treatment by ID
    @DeleteMapping("/delete/{treatmentId}")
    public ResponseEntity<Void> deleteTreatment(@PathVariable Long treatmentId) {
        boolean deleted = treatmentService.deleteTreatment(treatmentId);

        if (!deleted) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.noContent().build();
    }
}