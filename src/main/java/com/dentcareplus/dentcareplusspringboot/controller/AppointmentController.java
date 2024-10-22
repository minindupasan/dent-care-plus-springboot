package com.dentcareplus.dentcareplusspringboot.controller;

import com.dentcareplus.dentcareplusspringboot.entity.Appointment;
import com.dentcareplus.dentcareplusspringboot.entity.Patient;
import com.dentcareplus.dentcareplusspringboot.service.AppointmentService;
import com.dentcareplus.dentcareplusspringboot.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/appointments")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private PatientService patientService;

    // POST request to create an appointment (Only patient ID is used, details retrieved from Patient table)
    @PostMapping("/create/{patientId}")
    public ResponseEntity<Appointment> createAppointment(@PathVariable Long patientId, @RequestBody Appointment appointment) {
        // Retrieve the patient details using the patient ID
        Optional<Patient> patient = Optional.ofNullable(patientService.getPatientById(patientId));

        // Check if the patient exists
        if (!patient.isPresent()) {
            return ResponseEntity.notFound().build(); // Return 404 if patient not found
        }

        // Create the appointment by passing the patient details from the Patient table
        Appointment newAppointment = appointmentService.createAppointment(patientId, appointment);
        return ResponseEntity.ok(newAppointment); // Return 200 OK with the new appointment
    }


    // GET request to get all appointments
    @GetMapping
    public ResponseEntity<List<Appointment>> getAllAppointments() {
        List<Appointment> appointments = appointmentService.getAllAppointments();

        if (appointments.isEmpty()) {
            return ResponseEntity.noContent().build(); // Return 204 No Content if no appointments are found
        }

        return ResponseEntity.ok(appointments); // Return 200 OK with the list of appointments
    }

    // GET request to get a specific appointment by ID
    @GetMapping("/{appointmentId}")
    public ResponseEntity<Appointment> getAppointmentById(@PathVariable Long appointmentId) {
        Optional<Appointment> appointment = Optional.ofNullable(appointmentService.getAppointmentById(appointmentId));

        return appointment.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build()); // Return 404 Not Found if appointment doesn't exist
    }

    // PUT request to edit an appointment
    @PutMapping("/{appointmentId}")
    public ResponseEntity<Appointment> editAppointment(@PathVariable Long appointmentId, @RequestBody Appointment appointmentDetails) {
        Optional<Appointment> updatedAppointment = Optional.ofNullable(appointmentService.updateAppointment(appointmentId, appointmentDetails));

        return updatedAppointment.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build()); // Return 404 Not Found if appointment doesn't exist
    }

    // DELETE request to delete an appointment
    @DeleteMapping("/{appointmentId}")
    public ResponseEntity<Void> deleteAppointment(@PathVariable Long appointmentId) {
        Optional<Appointment> appointment = Optional.ofNullable(appointmentService.getAppointmentById(appointmentId));

        if (appointment.isPresent()) {
            boolean isDeleted = appointmentService.deleteAppointment(appointmentId);
            if (isDeleted) {
                return ResponseEntity.noContent().build(); // Return 204 No Content if successfully deleted
            }
        }

        return ResponseEntity.notFound().build(); // Return 404 Not Found if appointment doesn't exist
    }
}