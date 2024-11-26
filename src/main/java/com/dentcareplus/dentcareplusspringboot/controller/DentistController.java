package com.dentcareplus.dentcareplusspringboot.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/dentist")
@CrossOrigin
public class DentistController {

    @GetMapping("/dashboard")
    public ResponseEntity<?> getDashboard() {
        // This is a placeholder. In a real application, you would fetch and return actual data.
        return ResponseEntity.ok(Map.of(
                "message", "Welcome to the Dentist Dashboard",
                "appointments", 8,
                "patients", 30
        ));
    }
}

