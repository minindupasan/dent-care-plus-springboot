package com.dentcareplus.dentcareplusspringboot.controller;

import com.dentcareplus.dentcareplusspringboot.entity.Appointment;
import com.dentcareplus.dentcareplusspringboot.entity.Patient;
import com.dentcareplus.dentcareplusspringboot.service.AppointmentService;
import com.dentcareplus.dentcareplusspringboot.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/appointments")
@CrossOrigin(origins = {"https://dental-clinic-management-system-five.vercel.app", "http://localhost:3000"})
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private PatientService patientService;

    // POST request to create an appointment (Only patient ID is used, details retrieved from Patient table)
    @PostMapping("/create/{patientId}")
    public ResponseEntity<Appointment> createAppointment(@PathVariable Long patientId, @RequestBody Appointment appointment) {
        // Retrieve the patient details using the patient ID
        Patient patient = patientService.getPatientById(patientId);

        // Check if the patient exists
        if (patient == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // Return 404 if patient not found
        }

        // Create the appointment by passing the patient details from the Patient table
        Appointment newAppointment = appointmentService.createAppointment(patientId, appointment);
        return ResponseEntity.status(HttpStatus.CREATED).body(newAppointment); // Return 201 Created with the new appointment
    }

    // GET request to get all appointments
    @GetMapping
    public ResponseEntity<List<Appointment>> getAllAppointments() {
        List<Appointment> appointments = appointmentService.getAllAppointments();

        if (appointments.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build(); // Return 204 No Content if no appointments are found
        }

        return ResponseEntity.ok(appointments); // Return 200 OK with the list of appointments
    }

    // GET request to get a specific appointment by ID
    @GetMapping("/{appointmentId}")
    public ResponseEntity<Appointment> getAppointmentById(@PathVariable Long appointmentId) {
        Appointment appointment = appointmentService.getAppointmentById(appointmentId);

        if (appointment == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // Return 404 Not Found if appointment doesn't exist
        }

        return ResponseEntity.ok(appointment); // Return 200 OK with the appointment
    }

    // PUT request to edit an appointment
    @PutMapping("/update/{appointmentId}")
    public ResponseEntity<Appointment> editAppointment(@PathVariable Long appointmentId, @RequestBody Appointment appointmentDetails) {
        Appointment updatedAppointment = appointmentService.updateAppointment(appointmentId, appointmentDetails);

        if (updatedAppointment == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // Return 404 Not Found if appointment doesn't exist
        }

        return ResponseEntity.ok(updatedAppointment); // Return 200 OK with the updated appointment
    }

    // DELETE request to delete an appointment
    @DeleteMapping("/delete/{appointmentId}")
    public ResponseEntity<Void> deleteAppointment(@PathVariable Long appointmentId) {
        Appointment appointment = appointmentService.getAppointmentById(appointmentId);

        if (appointment == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // Return 404 Not Found if appointment doesn't exist
        }

        boolean isDeleted = appointmentService.deleteAppointment(appointmentId);
        if (isDeleted) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build(); // Return 204 No Content if successfully deleted
        }

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); // Return 500 if something went wrong during deletion
    }

}