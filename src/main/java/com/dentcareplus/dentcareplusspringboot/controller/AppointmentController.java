package com.dentcareplus.dentcareplusspringboot.controller;

import com.dentcareplus.dentcareplusspringboot.entity.Appointment;
import com.dentcareplus.dentcareplusspringboot.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/appointments")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    // POST request to create an appointment
    @PostMapping("/create/{patientId}")
    public ResponseEntity<Appointment> createAppointment(@PathVariable Long patientId, @RequestBody Appointment appointment) {
        Appointment newAppointment = appointmentService.createAppointment(patientId, appointment);
        return ResponseEntity.ok(newAppointment);
    }

    // Simple GET request to return "Hello, World!"
    @GetMapping("/test")
    public ResponseEntity<String> getHelloWorld() {
        return ResponseEntity.ok("Hello, World!");
    }
}