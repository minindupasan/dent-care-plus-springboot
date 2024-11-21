package com.dentcareplus.dentcareplusspringboot.service;

import com.dentcareplus.dentcareplusspringboot.entity.Denture;
import com.dentcareplus.dentcareplusspringboot.entity.Patient;
import com.dentcareplus.dentcareplusspringboot.repository.DentureRepository;
import com.dentcareplus.dentcareplusspringboot.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DentureService {

    private final DentureRepository dentureRepository;
    private final PatientRepository patientRepository;

    @Autowired
    public DentureService(DentureRepository dentureRepository, PatientRepository patientRepository) {
        this.dentureRepository = dentureRepository;
        this.patientRepository = patientRepository;
    }

    // Create a new denture record for a valid patient ID
    public Denture createDenture(Long patientId, Denture denture) {
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new IllegalArgumentException("Patient with ID " + patientId + " not found."));

        denture.setPatient(patient);
        return dentureRepository.save(denture);
    }

    // Get all denture records
    public List<Denture> getAllDentures() {
        List<Denture> dentures = dentureRepository.findAll();
        if (dentures.isEmpty()) {
            throw new IllegalStateException("No denture records found.");
        }
        return dentures;
    }

    // Get a denture record by ID
    public Denture getDentureById(Long dentureId) {
        return dentureRepository.findById(dentureId)
                .orElseThrow(() -> new IllegalArgumentException("Denture record with ID " + dentureId + " not found."));
    }

    // Update an existing denture record
    public Denture updateDenture(Long dentureId, Denture dentureDetails) {
        Denture denture = dentureRepository.findById(dentureId)
                .orElseThrow(() -> new IllegalArgumentException("Denture record with ID " + dentureId + " not found."));
        denture.setDentureType(dentureDetails.getDentureType());
        denture.setMaterialType(dentureDetails.getMaterialType());
        denture.setTrialDentureDate(dentureDetails.getTrialDentureDate());
        denture.setEstimatedDeliveryDate(dentureDetails.getEstimatedDeliveryDate());
        denture.setReceivedDate(dentureDetails.getReceivedDate());
        denture.setDeliveryStatus(dentureDetails.getDeliveryStatus());
        denture.setRemarks(dentureDetails.getRemarks());
        denture.setCost(dentureDetails.getCost());
        denture.setPaymentStatus(dentureDetails.getPaymentStatus());
        denture.setLabName(dentureDetails.getLabName());
        denture.setOrderDateToLab(dentureDetails.getOrderDateToLab());

        return dentureRepository.save(denture);
    }

    // Delete a denture record by ID
    public boolean deleteDenture(Long dentureId) {
        Denture denture = dentureRepository.findById(dentureId)
                .orElseThrow(() -> new IllegalArgumentException("Denture record with ID " + dentureId + " not found."));
        dentureRepository.delete(denture);
        return true;
    }
}