package com.dentcareplus.dentcareplusspringboot.service;

import com.dentcareplus.dentcareplusspringboot.entity.Prescription;
import com.dentcareplus.dentcareplusspringboot.repository.AppointmentRepository;
import com.dentcareplus.dentcareplusspringboot.repository.PrescriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PrescriptionService {

    @Autowired
    private PrescriptionRepository prescriptionRepository;

    @Autowired
    private AppointmentRepository appointmentRepository;

    // Create a prescription linked to an appointment ID
    public Prescription createPrescriptionByAppointmentId(Long appointmentId, Prescription prescription) {
        return appointmentRepository.findById(appointmentId).map(appointment -> {
            prescription.setAppointment(appointment); // Link the appointment
            return prescriptionRepository.save(prescription);
        }).orElseThrow(() -> new RuntimeException("Appointment not found with ID: " + appointmentId));
    }

    // Get all prescriptions
    public List<Prescription> getAllPrescriptions() {
        return prescriptionRepository.findAll();
    }

    // Get a prescription by ID
    public Optional<Prescription> getPrescriptionById(Long id) {
        return prescriptionRepository.findById(id);
    }

    // Update a prescription
    public Prescription updatePrescription(Long id, Prescription updatedPrescription) {
        return prescriptionRepository.findById(id).map(existing -> {
            existing.setAppointment(updatedPrescription.getAppointment());
            existing.setDateIssued(updatedPrescription.getDateIssued());
            existing.setNotes(updatedPrescription.getNotes());
            existing.setMedications(updatedPrescription.getMedications());
            return prescriptionRepository.save(existing);
        }).orElseThrow(() -> new RuntimeException("Prescription not found with ID: " + id));
    }

    // Delete a prescription
    public void deletePrescription(Long id) {
        prescriptionRepository.deleteById(id);
    }
}