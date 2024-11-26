package com.dentcareplus.dentcareplusspringboot.service;

import com.dentcareplus.dentcareplusspringboot.dto.DentureDTO;
import com.dentcareplus.dentcareplusspringboot.dto.PatientDTO;
import com.dentcareplus.dentcareplusspringboot.entity.Denture;
import com.dentcareplus.dentcareplusspringboot.entity.Patient;
import com.dentcareplus.dentcareplusspringboot.repository.DentureRepository;
import com.dentcareplus.dentcareplusspringboot.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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

    // Create a new Denture
    public DentureDTO createDenture(Long patientId, DentureDTO dentureDTO) {
        // Find the patient by ID
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Patient not found with ID: " + patientId));

        // Map the DTO to the Denture entity
        Denture denture = new Denture();
        denture.setDentureID(dentureDTO.getDentureID());
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
        denture.setPatient(patient); // Associate the patient

        // Save the denture entity to the database
        Denture savedDenture = dentureRepository.save(denture);

        // Map the saved entity to a DTO and return it
        return mapToDTO(savedDenture);
    }

    // Retrieve all Dentures
    public List<DentureDTO> getAllDentures() {
        return dentureRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    // Retrieve a single Denture by ID
    public DentureDTO getDentureById(Long dentureId) {
        Denture denture = dentureRepository.findById(dentureId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Denture not found with ID: " + dentureId));
        return mapToDTO(denture);
    }

    // Update an existing Denture
    public DentureDTO updateDenture(Long dentureId, DentureDTO dentureDTO) {
        // Fetch the existing Denture by ID
        Denture denture = dentureRepository.findById(dentureId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Denture not found with ID: " + dentureId));

        // Update only the necessary fields (don't reset the ID)
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

        // Save the updated Denture entity to the database
        Denture updatedDenture = dentureRepository.save(denture);

        // Return the updated DTO
        return mapToDTO(updatedDenture);
    }

    // Delete a Denture
    public void deleteDenture(Long dentureId) {
        Denture denture = dentureRepository.findById(dentureId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Denture not found with ID: " + dentureId));
        dentureRepository.delete(denture);
    }

    // Map Denture entity to DTO
    private DentureDTO mapToDTO(Denture denture) {
        DentureDTO dentureDTO = new DentureDTO();
        dentureDTO.setDentureID(denture.getDentureID());
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

        // Map associated patient information
        PatientDTO patientDTO = new PatientDTO(
                denture.getPatient().getPatientID(),
                denture.getPatient().getFirstName(),
                denture.getPatient().getLastName(),
                denture.getPatient().getGender(),
                denture.getPatient().getEmail(),
                denture.getPatient().getContactNo(),
                denture.getPatient().getDob(),
                denture.getPatient().getCreatedDate()
        );
        Patient patient = denture.getPatient();
        if (patient != null) {
            patientDTO.setPatientID(patient.getPatientID());
            patientDTO.setFirstName(patient.getFirstName());
            patientDTO.setLastName(patient.getLastName());
            patientDTO.setEmail(patient.getEmail());
            patientDTO.setContactNo(patient.getContactNo());
            patientDTO.setGender(patient.getGender());
            patientDTO.setDob(patient.getDob());
            patientDTO.setCreatedDate(patient.getCreatedDate());
        }

        dentureDTO.setPatient(patientDTO);
        return dentureDTO;
    }
}