package com.dentcareplus.dentcareplusspringboot.service;

import com.dentcareplus.dentcareplusspringboot.dto.TreatmentDTO;
import com.dentcareplus.dentcareplusspringboot.entity.Treatment;
import com.dentcareplus.dentcareplusspringboot.repository.TreatmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TreatmentService {

    private final TreatmentRepository treatmentRepository;

    @Autowired
    public TreatmentService(TreatmentRepository treatmentRepository) {
        this.treatmentRepository = treatmentRepository;
    }

    // Get all treatments as DTOs
    public List<TreatmentDTO> getAllTreatments() {
        List<Treatment> treatments = treatmentRepository.findAll();
        return treatments.stream()
                .map(treatment -> new TreatmentDTO(
                        treatment.getTreatmentID(),
                        treatment.getTreatmentType(),
                        treatment.getStartDate(),
                        treatment.getEndDate(),
                        treatment.getTotalPaid(),
                        treatment.getDueAmount(),
                        treatment.getNotes(),
                        treatment.getAppointmentID()))  // Mapping the appointment ID
                .collect(Collectors.toList());
    }

    // Get treatment by ID as DTO
    public TreatmentDTO getTreatmentById(Long id) {
        Treatment treatment = treatmentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Treatment with ID " + id + " not found."));

        return new TreatmentDTO(
                treatment.getTreatmentID(),
                treatment.getTreatmentType(),
                treatment.getStartDate(),
                treatment.getEndDate(),
                treatment.getTotalPaid(),
                treatment.getDueAmount(),
                treatment.getNotes(),
                treatment.getAppointmentID());  // Mapping the appointment ID
    }

    // Update treatment by ID
    public TreatmentDTO updateTreatment(Long treatmentId, TreatmentDTO treatmentDTO) {
        Optional<Treatment> existingTreatmentOptional = treatmentRepository.findById(treatmentId);

        if (!existingTreatmentOptional.isPresent()) {
            return null;  // Treatment not found
        }

        Treatment existingTreatment = existingTreatmentOptional.get();

        // Update the treatment fields
        existingTreatment.setTreatmentType(treatmentDTO.getTreatmentType());
        existingTreatment.setStartDate(treatmentDTO.getStartDate());
        existingTreatment.setEndDate(treatmentDTO.getEndDate());
        existingTreatment.setTotalPaid(treatmentDTO.getTotalPaid());
        existingTreatment.setDueAmount(treatmentDTO.getDueAmount());
        existingTreatment.setNotes(treatmentDTO.getNotes());

        // Save updated treatment
        Treatment updatedTreatment = treatmentRepository.save(existingTreatment);

        return new TreatmentDTO(
                updatedTreatment.getTreatmentID(),
                updatedTreatment.getTreatmentType(),
                updatedTreatment.getStartDate(),
                updatedTreatment.getEndDate(),
                updatedTreatment.getTotalPaid(),
                updatedTreatment.getDueAmount(),
                updatedTreatment.getNotes(),
                updatedTreatment.getAppointmentID());
    }

    // Delete treatment by ID
    public boolean deleteTreatment(Long treatmentId) {
        Optional<Treatment> treatmentOptional = treatmentRepository.findById(treatmentId);

        if (!treatmentOptional.isPresent()) {
            return false;  // Treatment not found
        }

        treatmentRepository.delete(treatmentOptional.get());
        return true;  // Treatment deleted
    }
}