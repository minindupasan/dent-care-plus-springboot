package com.dentcareplus.dentcareplusspringboot.service;

import com.dentcareplus.dentcareplusspringboot.dto.DentureDTO;
import com.dentcareplus.dentcareplusspringboot.entity.Denture;
import com.dentcareplus.dentcareplusspringboot.entity.Patient;
import com.dentcareplus.dentcareplusspringboot.repository.DentureRepository;
import com.dentcareplus.dentcareplusspringboot.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DentureService {

    private final DentureRepository dentureRepository;
    private final PatientRepository patientRepository;

    @Autowired
    public DentureService(DentureRepository dentureRepository, PatientRepository patientRepository) {
        this.dentureRepository = dentureRepository;
        this.patientRepository = patientRepository;
    }

    /**
     * Create a new denture record for a valid patient ID.
     *
     * @param patientId  - The patient ID for which the denture is being created.
     * @param dentureDTO - The data transfer object containing denture details.
     * @return - The saved Denture entity.
     */
    public Denture createDenture(Long patientId, DentureDTO dentureDTO) {
        // Fetching patient by patientId
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new IllegalArgumentException("Patient with ID " + patientId + " not found."));

        // Creating a new Denture entity from DTO
        Denture denture = new Denture();
        denture.setPatient(patient);
        denture.setDentureType(dentureDTO.getDentureType());
        denture.setMaterialType(dentureDTO.getMaterialType());
        denture.setTrialDentureDate(dentureDTO.getTrialDentureDate());
        denture.setEstimatedDeliveryDate(dentureDTO.getEstimatedDeliveryDate());
        denture.setReceivedDate(dentureDTO.getReceivedDate());
        denture.setRemarks(dentureDTO.getRemarks());
        denture.setCost(dentureDTO.getCost());
        denture.setPaymentStatus(dentureDTO.getPaymentStatus());
        denture.setDeliveryStatus(dentureDTO.getDeliveryStatus());
        denture.setLabName(dentureDTO.getLabName());
        denture.setOrderedDate(dentureDTO.getOrderedDate());

        // Save and return the created denture
        return dentureRepository.save(denture);
    }

    /**
     * Get all dentures.
     *
     * @return - List of all DentureDTO objects.
     */
    public List<DentureDTO> getAllDentures() {
        List<Denture> dentures = dentureRepository.findAll();
        return dentures.stream()
                .map(denture -> mapToDTO(denture)) // Map Denture to DentureDTO
                .collect(Collectors.toList());
    }

    /**
     * Get a denture by its ID.
     *
     * @param dentureId - The ID of the denture to retrieve.
     * @return - The DentureDTO for the specified denture.
     */
    public DentureDTO getDentureById(Long dentureId) {
        Denture denture = dentureRepository.findById(dentureId)
                .orElseThrow(() -> new IllegalArgumentException("Denture with ID " + dentureId + " not found."));
        return mapToDTO(denture);
    }

    /**
     * Update a denture's information.
     *
     * @param dentureId  - The ID of the denture to update.
     * @param dentureDTO - The new data to update the denture with.
     * @return - The updated DentureDTO.
     */
    public DentureDTO updateDenture(Long dentureId, DentureDTO dentureDTO) {
        Denture denture = dentureRepository.findById(dentureId)
                .orElseThrow(() -> new IllegalArgumentException("Denture with ID " + dentureId + " not found."));

        // Updating the Denture entity fields
        denture.setDentureType(dentureDTO.getDentureType());
        denture.setMaterialType(dentureDTO.getMaterialType());
        denture.setTrialDentureDate(dentureDTO.getTrialDentureDate());
        denture.setEstimatedDeliveryDate(dentureDTO.getEstimatedDeliveryDate());
        denture.setReceivedDate(dentureDTO.getReceivedDate());
        denture.setRemarks(dentureDTO.getRemarks());
        denture.setCost(dentureDTO.getCost());
        denture.setPaymentStatus(dentureDTO.getPaymentStatus());
        denture.setDeliveryStatus(dentureDTO.getDeliveryStatus());
        denture.setLabName(dentureDTO.getLabName());
        denture.setOrderedDate(dentureDTO.getOrderedDate());

        // Save and return the updated denture
        denture = dentureRepository.save(denture);
        return mapToDTO(denture);
    }

    /**
     * Delete a denture by its ID.
     *
     * @param dentureId - The ID of the denture to delete.
     */
    public void deleteDenture(Long dentureId) {
        Denture denture = dentureRepository.findById(dentureId)
                .orElseThrow(() -> new IllegalArgumentException("Denture with ID " + dentureId + " not found."));
        dentureRepository.delete(denture);
    }

    /**
     * Helper method to convert Denture entity to DentureDTO.
     *
     * @param denture - The Denture entity to map.
     * @return - The corresponding DentureDTO.
     */
    private DentureDTO mapToDTO(Denture denture) {
        DentureDTO dentureDTO = new DentureDTO();
        dentureDTO.setDentureType(denture.getDentureType());
        dentureDTO.setMaterialType(denture.getMaterialType());
        dentureDTO.setTrialDentureDate(denture.getTrialDentureDate());
        dentureDTO.setEstimatedDeliveryDate(denture.getEstimatedDeliveryDate());
        dentureDTO.setReceivedDate(denture.getReceivedDate());
        dentureDTO.setRemarks(denture.getRemarks());
        dentureDTO.setCost(denture.getCost());
        dentureDTO.setPaymentStatus(denture.getPaymentStatus());
        dentureDTO.setDeliveryStatus(denture.getDeliveryStatus());
        dentureDTO.setLabName(denture.getLabName());
        dentureDTO.setOrderedDate(denture.getOrderedDate());
        return dentureDTO;
    }
}