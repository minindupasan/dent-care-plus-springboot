package com.dentcareplus.dentcareplusspringboot.controller;

import com.dentcareplus.dentcareplusspringboot.entity.Denture;
import com.dentcareplus.dentcareplusspringboot.service.DentureService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/dentures")
public class DentureController {

    private final DentureService dentureService;

    public DentureController(DentureService dentureService) {
        this.dentureService = dentureService;
    }

    // Create a new Denture
    @PostMapping("/create/{patientId}")
    public ResponseEntity<Denture> createDenture(
            @PathVariable Long patientId,
            @RequestBody @Valid Denture denture) {
        Denture createdDenture = dentureService.createDenture(patientId, denture);
        return ResponseEntity.ok(createdDenture);
    }

    // Get all Dentures
    @GetMapping
    public ResponseEntity<List<Denture>> getAllDentures() {
        List<Denture> dentures = dentureService.getAllDentures();
        return ResponseEntity.ok(dentures);
    }

    // Get a Denture by ID
    @GetMapping("/{dentureId}")
    public ResponseEntity<Denture> getDentureById(@PathVariable Long dentureId) {
        Denture denture = dentureService.getDentureById(dentureId);
        return ResponseEntity.ok(denture);
    }

    // Update a Denture
    @PutMapping("/update/{dentureId}")
    public ResponseEntity<Denture> updateDenture(
            @PathVariable Long dentureId,
            @RequestBody @Valid Denture dentureDetails) {
        Denture updatedDenture = dentureService.updateDenture(dentureId, dentureDetails);
        return ResponseEntity.ok(updatedDenture);
    }

    // Delete a Denture
    @DeleteMapping("/delete/{dentureId}")
    public ResponseEntity<Void> deleteDenture(@PathVariable Long dentureId) {
        dentureService.deleteDenture(dentureId);
        return ResponseEntity.noContent().build();
    }
}