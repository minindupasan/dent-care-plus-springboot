package com.dentcareplus.dentcareplusspringboot.service;

import com.dentcareplus.dentcareplusspringboot.dto.AppointmentDTO;
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

    public AppointmentDTO createAppointment(Long patientId, Appointment appointment) {
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new IllegalArgumentException("Patient with ID " + patientId + " not found."));

        appointment.setPatient(patient);

        if (appointment.getTreatment() != null) {
            Treatment treatment = appointment.getTreatment();
            treatment.setAppointment(appointment); // Ensure the Treatment is linked to the Appointment
        }

        Appointment savedAppointment = appointmentRepository.save(appointment);

        return new AppointmentDTO(
                savedAppointment.getAppointmentID(),
                savedAppointment.getPatient(),
                savedAppointment.getAppointmentDate(),
                savedAppointment.getAppointmentTime(),
                savedAppointment.getReason(),
                savedAppointment.getStatus()
        );
    }

    public List<AppointmentDTO> getAllAppointments() {
        return appointmentRepository.findAll().stream()
                .map(appointment -> new AppointmentDTO(
                        appointment.getAppointmentID(),
                        appointment.getPatient(),
                        appointment.getAppointmentDate(),
                        appointment.getAppointmentTime(),
                        appointment.getReason(),
                        appointment.getStatus()))
                .collect(Collectors.toList());
    }

    public AppointmentDTO getAppointmentById(Long id) {
        Appointment appointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Appointment with ID " + id + " not found."));

        return new AppointmentDTO(
                appointment.getAppointmentID(),
                appointment.getPatient(),
                appointment.getAppointmentDate(),
                appointment.getAppointmentTime(),
                appointment.getReason(),
                appointment.getStatus()
        );
    }

    public AppointmentDTO updateAppointment(Long id, Appointment appointmentDetails) {
        Appointment appointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Appointment with ID " + id + " not found."));

        appointment.setAppointmentDate(appointmentDetails.getAppointmentDate());
        appointment.setAppointmentTime(appointmentDetails.getAppointmentTime());
        appointment.setReason(appointmentDetails.getReason());
        appointment.setStatus(appointmentDetails.getStatus());

        Appointment updatedAppointment = appointmentRepository.save(appointment);

        return new AppointmentDTO(
                updatedAppointment.getAppointmentID(),
                updatedAppointment.getPatient(),
                updatedAppointment.getAppointmentDate(),
                updatedAppointment.getAppointmentTime(),
                updatedAppointment.getReason(),
                updatedAppointment.getStatus()
        );
    }

    public void deleteAppointment(Long id) {
        Appointment appointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Appointment with ID " + id + " not found."));
        appointmentRepository.delete(appointment);
    }
}