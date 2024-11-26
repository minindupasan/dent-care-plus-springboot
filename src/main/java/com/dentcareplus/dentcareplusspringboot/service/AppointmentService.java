package com.dentcareplus.dentcareplusspringboot.service;

import com.dentcareplus.dentcareplusspringboot.dto.AppointmentDTO;
import com.dentcareplus.dentcareplusspringboot.dto.PatientDTO;
import com.dentcareplus.dentcareplusspringboot.entity.Appointment;
import com.dentcareplus.dentcareplusspringboot.entity.Patient;
import com.dentcareplus.dentcareplusspringboot.entity.Treatment;
import com.dentcareplus.dentcareplusspringboot.repository.AppointmentRepository;
import com.dentcareplus.dentcareplusspringboot.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final PatientRepository patientRepository;

    @Autowired
    public AppointmentService(AppointmentRepository appointmentRepository, PatientRepository patientRepository) {
        this.appointmentRepository = appointmentRepository;
        this.patientRepository = patientRepository;
    }

    // Create a new appointment
    public AppointmentDTO createAppointment(Long patientId, Appointment appointment) {
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new IllegalArgumentException("Patient with ID " + patientId + " not found."));

        appointment.setPatient(patient);

        // Link treatment to appointment if provided
        if (appointment.getTreatment() != null) {
            Treatment treatment = appointment.getTreatment();
            treatment.setAppointment(appointment);
        }

        Appointment savedAppointment = appointmentRepository.save(appointment);
        return mapToAppointmentDTO(savedAppointment);
    }

    // Retrieve all appointments
    public List<AppointmentDTO> getAllAppointments() {
        return appointmentRepository.findAll().stream()
                .map(this::mapToAppointmentDTO)
                .collect(Collectors.toList());
    }

    // Retrieve an appointment by ID
    public AppointmentDTO getAppointmentById(Long id) {
        Appointment appointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Appointment with ID " + id + " not found."));
        return mapToAppointmentDTO(appointment);
    }

    // Update an existing appointment
    public AppointmentDTO updateAppointment(Long id, Appointment appointmentDetails) {
        Appointment appointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Appointment with ID " + id + " not found."));

        appointment.setAppointmentDate(appointmentDetails.getAppointmentDate());
        appointment.setAppointmentTime(appointmentDetails.getAppointmentTime());
        appointment.setReason(appointmentDetails.getReason());
        appointment.setStatus(appointmentDetails.getStatus());

        Appointment updatedAppointment = appointmentRepository.save(appointment);
        return mapToAppointmentDTO(updatedAppointment);
    }

    // Delete an appointment
    public void deleteAppointment(Long id) {
        Appointment appointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Appointment with ID " + id + " not found."));
        appointmentRepository.delete(appointment);
    }

    // Helper method to map Appointment entity to AppointmentDTO
    private AppointmentDTO mapToAppointmentDTO(Appointment appointment) {
        Patient patient = appointment.getPatient();

        // Map Patient entity to PatientDTO
        PatientDTO patientDTO = new PatientDTO(
                patient.getPatientID(),
                patient.getFirstName(),
                patient.getLastName(),
                patient.getGender(),
                patient.getEmail(),
                patient.getContactNo(),
                patient.getDob(),
                patient.getCreatedDate()
        );

        return new AppointmentDTO(
                appointment.getAppointmentID(),
                patientDTO,
                appointment.getAppointmentDate(),
                appointment.getAppointmentTime(),
                appointment.getReason(),
                appointment.getStatus()
        );
    }
}