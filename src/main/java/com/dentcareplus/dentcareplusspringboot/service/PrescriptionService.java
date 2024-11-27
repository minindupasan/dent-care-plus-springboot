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

    public PrescriptionDTO createPrescriptionByAppointmentId(Long appointmentId, PrescriptionDTO prescriptionDTO) {
        return appointmentRepository.findById(appointmentId).map(appointment -> {
            Prescription prescription = convertToEntity(prescriptionDTO);
            prescription.setAppointment(appointment);
            Prescription savedPrescription = prescriptionRepository.save(prescription);
            return convertToDTO(savedPrescription);
        }).orElseThrow(() -> new RuntimeException("Appointment not found with ID: " + appointmentId));
    }

    public List<PrescriptionDTO> getAllPrescriptions() {
        return prescriptionRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public Optional<PrescriptionDTO> getPrescriptionById(Long id) {
        return prescriptionRepository.findById(id).map(this::convertToDTO);
    }

    public PrescriptionDTO updatePrescription(Long id, PrescriptionDTO updatedPrescriptionDTO) {
        return prescriptionRepository.findById(id).map(existing -> {
            Prescription updatedPrescription = convertToEntity(updatedPrescriptionDTO);
            existing.setDateIssued(updatedPrescription.getDateIssued());
            existing.setNotes(updatedPrescription.getNotes());
            existing.setMedications(updatedPrescription.getMedications());
            Prescription savedPrescription = prescriptionRepository.save(existing);
            return convertToDTO(savedPrescription);
        }).orElseThrow(() -> new RuntimeException("Prescription not found with ID: " + id));
    }

    public void deletePrescription(Long id) {
        prescriptionRepository.deleteById(id);
    }

    private PrescriptionDTO convertToDTO(Prescription prescription) {
        PrescriptionDTO dto = new PrescriptionDTO();
        dto.setPrescriptionId(prescription.getPrescriptionId());
        dto.setAppointmentId(prescription.getAppointment().getAppointmentID());
        dto.setDateIssued(prescription.getDateIssued());
        dto.setNotes(prescription.getNotes());
        dto.setMedications(prescription.getMedications().stream()
                .map(this::convertMedicationToDTO)
                .collect(Collectors.toList()));
        return dto;
    }

    private Prescription convertToEntity(PrescriptionDTO dto) {
        Prescription prescription = new Prescription();
        prescription.setPrescriptionId(dto.getPrescriptionId());
        prescription.setDateIssued(dto.getDateIssued());
        prescription.setNotes(dto.getNotes());
        prescription.setMedications(dto.getMedications().stream()
                .map(this::convertMedicationToEntity)
                .collect(Collectors.toList()));
        return prescription;
    }

    private PrescriptionDTO.MedicationDTO convertMedicationToDTO(Prescription.Medication medication) {
        PrescriptionDTO.MedicationDTO dto = new PrescriptionDTO.MedicationDTO();
        dto.setMedicationName(medication.getMedicationName());
        dto.setDosage(medication.getDosage());
        dto.setFrequency(medication.getFrequency());
        dto.setDuration(medication.getDuration());
        dto.setFrequencyDisplay(medication.getFrequencyDisplay());
        dto.setDurationDisplay(medication.getDurationDisplay());
        return dto;
    }

    private Prescription.Medication convertMedicationToEntity(PrescriptionDTO.MedicationDTO dto) {
        Prescription.Medication medication = new Prescription.Medication();
        medication.setMedicationName(dto.getMedicationName());
        medication.setDosage(dto.getDosage());
        medication.setFrequency(dto.getFrequency());
        medication.setDuration(dto.getDuration());
        return medication;
    }
}

