package com.dentcareplus.dentcareplusspringboot.service;

import com.dentcareplus.dentcareplusspringboot.entity.Treatment;
import com.dentcareplus.dentcareplusspringboot.repository.TreatmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TreatmentService {
    private final TreatmentRepository treatmentRepository;

    @Autowired
    public TreatmentService(TreatmentRepository treatmentRepository) {
        this.treatmentRepository = treatmentRepository;
    }

    public List<Treatment> getAllTreatments() {
        return treatmentRepository.findAll();
    }

    public Treatment getTreatmentById(Long id) {
        return treatmentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Treatment with ID " + id + " not found."));
    }

    public Treatment updateTreatment(Long id, Treatment treatmentDetails) {
        Treatment treatment = treatmentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Treatment with ID " + id + " not found."));

        treatment.setTreatmentType(treatmentDetails.getTreatmentType());
        treatment.setStartDate(treatmentDetails.getStartDate());
        treatment.setEndDate(treatmentDetails.getEndDate());
        treatment.setTotalPaid(treatmentDetails.getTotalPaid());
        treatment.setDueAmount(treatmentDetails.getDueAmount());
        treatment.setNotes(treatmentDetails.getNotes());

        return treatmentRepository.save(treatment);
    }

    public void deleteTreatment(Long id) {
        Treatment treatment = treatmentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Treatment with ID " + id + " not found."));
        treatmentRepository.delete(treatment);
    }
}