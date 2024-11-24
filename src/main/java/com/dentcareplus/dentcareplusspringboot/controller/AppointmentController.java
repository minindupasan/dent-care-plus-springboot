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
@CrossOrigin(origins = {"https://dental-clinic-management-system-five.vercel.app", "http://localhost:3000"})
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
        List<AppointmentDTO> appointments = appointmentService.getAllAppointments();
        return ResponseEntity.ok(appointments);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AppointmentDTO> getAppointmentById(@PathVariable Long id) {
        AppointmentDTO appointment = appointmentService.getAppointmentById(id);
        return ResponseEntity.ok(appointment);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AppointmentDTO> updateAppointment(@PathVariable Long id, @RequestBody Appointment appointmentDetails) {
        AppointmentDTO updatedAppointment = appointmentService.updateAppointment(id, appointmentDetails);
        return ResponseEntity.ok(updatedAppointment);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAppointment(@PathVariable Long id) {
        appointmentService.deleteAppointment(id);
        return ResponseEntity.noContent().build();
    }
}