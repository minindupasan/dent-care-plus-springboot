package com.dentcareplus.dentcareplusspringboot.controller;

import com.dentcareplus.dentcareplusspringboot.dto.DentureDTO;
import com.dentcareplus.dentcareplusspringboot.entity.Denture;
import com.dentcareplus.dentcareplusspringboot.service.DentureService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/dentures")
@CrossOrigin(origins = {"https://dental-clinic-management-system-five.vercel.app", "http://localhost:3000"})
public class DentureController {

    private final DentureService dentureService;

    public DentureController(DentureService dentureService) {
        this.dentureService = dentureService;
    }

    // Create a new Denture
    @PostMapping("/create/{patientId}")
    public ResponseEntity<Denture> createDenture(
            @PathVariable Long patientId,
            @RequestBody @Valid DentureDTO dentureDTO) {
        Denture createdDenture = dentureService.createDenture(patientId, dentureDTO);
        return ResponseEntity.ok(createdDenture);
    }

    // Get all Dentures
    @GetMapping
    public ResponseEntity<List<DentureDTO>> getAllDentures() {
        List<DentureDTO> dentures = dentureService.getAllDentures();
        return ResponseEntity.ok(dentures);
    }

    // Get a Denture by ID
    @GetMapping("/{dentureId}")
    public ResponseEntity<DentureDTO> getDentureById(@PathVariable Long dentureId) {
        DentureDTO denture = dentureService.getDentureById(dentureId);
        return ResponseEntity.ok(denture);
    }

    // Update a Denture
    @PutMapping("/update/{dentureId}")
    public ResponseEntity<DentureDTO> updateDenture(
            @PathVariable Long dentureId,
            @RequestBody @Valid DentureDTO dentureDTO) {
        DentureDTO updatedDenture = dentureService.updateDenture(dentureId, dentureDTO);
        return ResponseEntity.ok(updatedDenture);
    }

    // Delete a Denture
    @DeleteMapping("/delete/{dentureId}")
    public ResponseEntity<Void> deleteDenture(@PathVariable Long dentureId) {
        dentureService.deleteDenture(dentureId);
        return ResponseEntity.noContent().build();
    }
}