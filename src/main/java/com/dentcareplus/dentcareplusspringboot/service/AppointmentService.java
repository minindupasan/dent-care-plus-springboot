package com.dentcareplus.dentcareplusspringboot.service;

import com.dentcareplus.dentcareplusspringboot.entity.Appointment;
import com.dentcareplus.dentcareplusspringboot.entity.Patient;
import com.dentcareplus.dentcareplusspringboot.repository.AppointmentRepository;
import com.dentcareplus.dentcareplusspringboot.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentService {
    private final AppointmentRepository appointmentRepository;
    private final PatientRepository patientRepository;

    @Autowired
    public AppointmentService(AppointmentRepository appointmentRepository, PatientRepository patientRepository) {
        this.appointmentRepository = appointmentRepository;
        this.patientRepository = patientRepository;
    }

    // Create a new appointment with a valid patient ID
    public Appointment createAppointment(Long patientId, Appointment appointment) {
        // Check if the patient exists
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new IllegalArgumentException("Patient with ID " + patientId + " not found."));

        // Associate the patient with the appointment
        appointment.setPatient(patient);
        return appointmentRepository.save(appointment);
    }

    // Get all appointments
    public List<Appointment> getAllAppointments() {
        List<Appointment> appointments = appointmentRepository.findAll();
        if (appointments.isEmpty()) {
            throw new IllegalStateException("No appointments found.");
        }
        return appointments;
    }

    // Get appointment by ID
    public Appointment getAppointmentById(Long id) {
        return appointmentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Appointment with ID " + id + " not found."));
    }

    // Update an existing appointment
    public Appointment updateAppointment(Long id, Appointment appointmentDetails) {
        // Find the existing appointment
        Appointment appointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Appointment with ID " + id + " not found."));

        // Update appointment details
        appointment.setAppointmentDate(appointmentDetails.getAppointmentDate());
        appointment.setAppointmentTime(appointmentDetails.getAppointmentTime());
        appointment.setReason(appointmentDetails.getReason());
        appointment.setStatus(appointmentDetails.getStatus());

        return appointmentRepository.save(appointment);  // Save and return the updated appointment
    }

    // Delete an appointment by ID
    public boolean deleteAppointment(Long id) {
        Appointment appointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Appointment with ID " + id + " not found."));
        appointmentRepository.delete(appointment);
        return true;  // Return true if deletion is successful
    }
}