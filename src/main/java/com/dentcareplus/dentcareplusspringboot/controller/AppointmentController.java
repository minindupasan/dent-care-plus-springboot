package com.dentcareplus.dentcareplusspringboot.controller;

import com.dentcareplus.dentcareplusspringboot.dto.AppointmentDTO;
import com.dentcareplus.dentcareplusspringboot.entity.Appointment;
import com.dentcareplus.dentcareplusspringboot.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/appointments")
@CrossOrigin
public class AppointmentController {
    @Autowired
    private AppointmentService appointmentService;

    @PostMapping("/create/{patientId}")
    public ResponseEntity<AppointmentDTO> createAppointment(@PathVariable Long patientId, @RequestBody Appointment appointment) {
        AppointmentDTO newAppointment = appointmentService.createAppointment(patientId, appointment);
        return ResponseEntity.status(HttpStatus.CREATED).body(newAppointment);
    }

    @GetMapping
    public ResponseEntity<List<AppointmentDTO>> getAllAppointments() {
        return ResponseEntity.ok(appointmentService.getAllAppointments());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AppointmentDTO> getAppointmentById(@PathVariable Long id) {
        return ResponseEntity.ok(appointmentService.getAppointmentById(id));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<AppointmentDTO> updateAppointment(@PathVariable Long id, @RequestBody Appointment appointmentDetails) {
        return ResponseEntity.ok(appointmentService.updateAppointment(id, appointmentDetails));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteAppointment(@PathVariable Long id) {
        appointmentService.deleteAppointment(id);
        return ResponseEntity.noContent().build();
    }
}