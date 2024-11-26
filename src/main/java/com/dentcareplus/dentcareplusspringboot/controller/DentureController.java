package com.dentcareplus.dentcareplusspringboot.controller;

import com.dentcareplus.dentcareplusspringboot.dto.DentureDTO;
import com.dentcareplus.dentcareplusspringboot.service.DentureService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/dentures")
@CrossOrigin
public class DentureController {

    private final DentureService dentureService;

    public DentureController(DentureService dentureService) {
        this.dentureService = dentureService;
    }

    // Create a new Denture for a specific Patient
    @PostMapping("/create/{patientID}")
    public ResponseEntity<DentureDTO> createDenture(
            @PathVariable Long patientID,
            @RequestBody @Valid DentureDTO dentureDTO) {
        // Create a new Denture for the patient and return the created DentureDTO
        DentureDTO createdDenture = dentureService.createDenture(patientID, dentureDTO);
        return ResponseEntity.ok(createdDenture);
    }

    // Get all Dentures
    @GetMapping
    public ResponseEntity<List<DentureDTO>> getAllDentures() {
        // Retrieve all dentures and return them as a list of DentureDTOs
        List<DentureDTO> dentures = dentureService.getAllDentures();
        return ResponseEntity.ok(dentures);
    }

    // Get a Denture by its ID
    @GetMapping("/{dentureID}")
    public ResponseEntity<DentureDTO> getDentureById(@PathVariable Long dentureID) {
        // Retrieve a specific denture by its ID and return it as a DentureDTO
        DentureDTO denture = dentureService.getDentureById(dentureID);
        return ResponseEntity.ok(denture);
    }

    // Update a Denture by its ID
    @PutMapping("/update/{dentureID}")
    public ResponseEntity<DentureDTO> updateDenture(
            @PathVariable Long dentureID,
            @RequestBody @Valid DentureDTO dentureDTO) {
        // Update the denture information and return the updated DentureDTO
        DentureDTO updatedDenture = dentureService.updateDenture(dentureID, dentureDTO);
        return ResponseEntity.ok(updatedDenture);
    }

    // Delete a Denture by its ID
    @DeleteMapping("/delete/{dentureID}")
    public ResponseEntity<Void> deleteDenture(@PathVariable Long dentureID) {
        // Delete the denture by its ID and return no content status
        dentureService.deleteDenture(dentureID);
        return ResponseEntity.noContent().build();
    }
}