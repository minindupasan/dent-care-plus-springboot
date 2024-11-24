package com.dentcareplus.dentcareplusspringboot.service;

import com.dentcareplus.dentcareplusspringboot.entity.Appointment;
import com.dentcareplus.dentcareplusspringboot.entity.Patient;
import com.dentcareplus.dentcareplusspringboot.entity.Treatment;
import com.dentcareplus.dentcareplusspringboot.repository.AppointmentRepository;
import com.dentcareplus.dentcareplusspringboot.repository.PatientRepository;
import com.dentcareplus.dentcareplusspringboot.repository.TreatmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentService {
    private final AppointmentRepository appointmentRepository;
    private final PatientRepository patientRepository;
    private final TreatmentRepository treatmentRepository;

    @Autowired
    public AppointmentService(AppointmentRepository appointmentRepository, PatientRepository patientRepository, TreatmentRepository treatmentRepository) {
        this.appointmentRepository = appointmentRepository;
        this.patientRepository = patientRepository;
        this.treatmentRepository = treatmentRepository;
    }

    // Create a new appointment with a valid patient ID and treatment type
    public Appointment createAppointment(Long patientId, Appointment appointment) {
        // Check if the patient exists
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new IllegalArgumentException("Patient with ID " + patientId + " not found."));

        // Associate the patient with the appointment
        appointment.setPatient(patient);

        // Create the treatment and associate it with the appointment
        Treatment treatment = appointment.getTreatment();
        if (treatment == null || treatment.getTreatmentType() == null) {
            throw new IllegalArgumentException("Treatment type must be specified.");
        }
        treatment.setAppointment(appointment);

        // Save the appointment (which will cascade to save the treatment)
        return appointmentRepository.save(appointment);
    }

    // Other methods (getAllAppointments, getAppointmentById, etc.) remain the same
}