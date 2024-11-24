package com.dentcareplus.dentcareplusspringboot.service;

import com.dentcareplus.dentcareplusspringboot.dto.PatientDTO;
import com.dentcareplus.dentcareplusspringboot.dto.TreatmentDTO;
import com.dentcareplus.dentcareplusspringboot.entity.Patient;
import com.dentcareplus.dentcareplusspringboot.entity.Treatment;
import com.dentcareplus.dentcareplusspringboot.repository.PatientRepository;
import com.dentcareplus.dentcareplusspringboot.repository.TreatmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TreatmentService {

    private final TreatmentRepository treatmentRepository;
    private final PatientRepository patientRepository;

    @Autowired
    public TreatmentService(TreatmentRepository treatmentRepository, PatientRepository patientRepository) {
        this.treatmentRepository = treatmentRepository;
        this.patientRepository = patientRepository;
    }

    // Get all treatments as DTOs with patient details
    public List<TreatmentDTO> getAllTreatments() {
        List<Treatment> treatments = treatmentRepository.findAll();
        return treatments.stream()
                .map(treatment -> {
                    // Fetch patient details using getter methods
                    Patient patient = treatment.getAppointment().getPatient();  // Assuming Treatment has Appointment with Patient
                    PatientDTO patientDTO = new PatientDTO(
                            patient.getPatientID(),
                            patient.getFirstName(),
                            patient.getLastName(),
                            patient.getEmail(),
                            patient.getContactNo(),
                            patient.getGender(),
                            patient.getDob(),
                            patient.getCreatedDate()
                    );
                    return new TreatmentDTO(
                            treatment.getTreatmentID(),
                            treatment.getTreatmentType(),
                            treatment.getStartDate(),
                            treatment.getEndDate(),
                            treatment.getTotalPaid(),
                            treatment.getDueAmount(),
                            treatment.getPaymentStatus(),
                            treatment.getTreatmentStatus(),
                            treatment.getNotes(),
                            treatment.getAppointmentID(),
                            patientDTO  // Add patient details
                    );
                })
                .collect(Collectors.toList());
    }

    // Get treatment by ID as DTO with patient details
    public TreatmentDTO getTreatmentById(Long id) {
        Treatment treatment = treatmentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Treatment with ID " + id + " not found."));

        // Fetch patient details using getter methods
        Patient patient = treatment.getAppointment().getPatient();
        PatientDTO patientDTO = new PatientDTO(
                patient.getPatientID(),
                patient.getFirstName(),
                patient.getLastName(),
                patient.getEmail(),
                patient.getContactNo(),
                patient.getGender(),
                patient.getDob(),
                patient.getCreatedDate()
        );

        return new TreatmentDTO(
                treatment.getTreatmentID(),
                treatment.getTreatmentType(),
                treatment.getStartDate(),
                treatment.getEndDate(),
                treatment.getTotalPaid(),
                treatment.getDueAmount(),
                treatment.getPaymentStatus(),
                treatment.getTreatmentStatus(),
                treatment.getNotes(),
                treatment.getAppointmentID(),
                patientDTO  // Add patient details
        );
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
        existingTreatment.setPaymentStatus(treatmentDTO.getPaymentStatus());
        existingTreatment.setTreatmentStatus(treatmentDTO.getTreatmentStatus());
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
                updatedTreatment.getPaymentStatus(),
                updatedTreatment.getTreatmentStatus(),
                updatedTreatment.getNotes(),
                updatedTreatment.getAppointmentID(),
                treatmentDTO.getPatient()  // Return updated patient details from the DTO
        );
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