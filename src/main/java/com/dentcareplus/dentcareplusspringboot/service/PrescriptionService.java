package com.dentcareplus.dentcareplusspringboot.service;

import com.dentcareplus.dentcareplusspringboot.dto.PrescriptionDTO;
import com.dentcareplus.dentcareplusspringboot.entity.Prescription;
import com.dentcareplus.dentcareplusspringboot.repository.AppointmentRepository;
import com.dentcareplus.dentcareplusspringboot.repository.PrescriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PrescriptionService {

    @Autowired
    private PrescriptionRepository prescriptionRepository;

    @Autowired
    private AppointmentRepository appointmentRepository;

    // Create a prescription linked to an appointment ID
    public PrescriptionDTO createPrescriptionByAppointmentId(Long appointmentId, PrescriptionDTO prescriptionDTO) {
        return appointmentRepository.findById(appointmentId).map(appointment -> {
            Prescription prescription = convertToEntity(prescriptionDTO);
            prescription.setAppointment(appointment);
            Prescription savedPrescription = prescriptionRepository.save(prescription);
            return convertToDTO(savedPrescription);
        }).orElseThrow(() -> new RuntimeException("Appointment not found with ID: " + appointmentId));
    }

    // Get all prescriptions
    public List<PrescriptionDTO> getAllPrescriptions() {
        List<Prescription> prescriptions = prescriptionRepository.findAll();
        return prescriptions.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    // Get a prescription by ID
    public Optional<PrescriptionDTO> getPrescriptionById(Long id) {
        return prescriptionRepository.findById(id).map(this::convertToDTO);
    }

    // Update a prescription
    public PrescriptionDTO updatePrescription(Long id, PrescriptionDTO updatedPrescriptionDTO) {
        return prescriptionRepository.findById(id).map(existing -> {
            Prescription updatedPrescription = convertToEntity(updatedPrescriptionDTO);
            updatedPrescription.setPrescriptionId(existing.getPrescriptionId());
            updatedPrescription.setAppointment(existing.getAppointment());
            Prescription savedPrescription = prescriptionRepository.save(updatedPrescription);
            return convertToDTO(savedPrescription);
        }).orElseThrow(() -> new RuntimeException("Prescription not found with ID: " + id));
    }

    // Delete a prescription
    public void deletePrescription(Long id) {
        prescriptionRepository.deleteById(id);
    }

    // Convert Prescription Entity to DTO
    private PrescriptionDTO convertToDTO(Prescription prescription) {
        PrescriptionDTO dto = new PrescriptionDTO();
        dto.setPrescriptionId(prescription.getPrescriptionId());
        dto.setAppointmentId(prescription.getAppointment().getAppointmentID());
        dto.setDateIssued(prescription.getDateIssued());
        dto.setNotes(prescription.getNotes());
        dto.setMedications(
                prescription.getMedications().stream().map(medication -> {
                    PrescriptionDTO.MedicationDTO medDTO = new PrescriptionDTO.MedicationDTO();
                    medDTO.setMedicationName(medication.getMedicationName());
                    medDTO.setDosage(medication.getDosage());
                    medDTO.setFrequency(medication.getFrequency());
                    medDTO.setDuration(medication.getDuration());
                    medDTO.setFrequencyDisplay(medication.getFrequencyDisplay());
                    medDTO.setDurationDisplay(medication.getDurationDisplay());
                    return medDTO;
                }).collect(Collectors.toList())
        );
        return dto;
    }

    // Convert PrescriptionDTO to Entity
    private Prescription convertToEntity(PrescriptionDTO dto) {
        Prescription prescription = new Prescription();
        prescription.setDateIssued(dto.getDateIssued());
        prescription.setNotes(dto.getNotes());
        prescription.setMedications(
                dto.getMedications().stream().map(medDTO -> {
                    Prescription.Medication medication = new Prescription.Medication();
                    medication.setMedicationName(medDTO.getMedicationName());
                    medication.setDosage(medDTO.getDosage());
                    medication.setFrequency(medDTO.getFrequency());
                    medication.setDuration(medDTO.getDuration());
                    return medication;
                }).collect(Collectors.toList())
        );
        return prescription;
    }
}