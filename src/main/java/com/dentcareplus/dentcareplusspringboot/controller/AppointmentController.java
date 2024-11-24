package com.dentcareplus.dentcareplusspringboot.controller;

import com.dentcareplus.dentcareplusspringboot.entity.Appointment;
import com.dentcareplus.dentcareplusspringboot.entity.Patient;
import com.dentcareplus.dentcareplusspringboot.service.AppointmentService;
import com.dentcareplus.dentcareplusspringboot.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/appointments")
@CrossOrigin(origins = {"https://dental-clinic-management-system-five.vercel.app", "http://localhost:3000"})
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private PatientService patientService;

    // POST request to create an appointment with treatment
    @PostMapping("/create/{patientId}")
    public ResponseEntity<Appointment> createAppointment(@PathVariable Long patientId, @RequestBody Appointment appointment) {
        // Retrieve the patient details using the patient ID
        Patient patient = patientService.getPatientById(patientId);

        // Check if the patient exists
        if (patient == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // Return 404 if patient not found
        }

        // Create the appointment by passing the patient details and treatment type
        Appointment newAppointment = appointmentService.createAppointment(patientId, appointment);
        return ResponseEntity.status(HttpStatus.CREATED).body(newAppointment); // Return 201 Created with the new appointment
    }

    // Other endpoints remain the same (GET, PUT, DELETE)
}