package com.dentcareplus.dentcareplusspringboot.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/assistant")
@CrossOrigin
public class AssistantController {

    @GetMapping("/dashboard")
    public ResponseEntity<?> getDashboard() {
        // This is a placeholder. In a real application, you would fetch and return actual data.
        return ResponseEntity.ok(Map.of(
                "message", "Welcome to the Assistant Dashboard",
                "appointments", 5,
                "patients", 20
        ));
    }
}

