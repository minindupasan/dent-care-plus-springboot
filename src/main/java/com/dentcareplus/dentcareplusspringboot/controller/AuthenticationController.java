package com.dentcareplus.dentcareplusspringboot.controller;

import com.dentcareplus.dentcareplusspringboot.dto.JwtRequest;
import com.dentcareplus.dentcareplusspringboot.enums.RoleEnum;
import com.dentcareplus.dentcareplusspringboot.service.AuthService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

    private final AuthService authService;

    public AuthenticationController(AuthService authService) {
        this.authService = authService;
    }

    // Registration method that accepts role from the request
    @PostMapping("/register")
    public String register(@RequestBody JwtRequest request, @RequestParam RoleEnum role) {
        authService.registerUser(request.getUsername(), request.getPassword(), role);
        return "User registered successfully with role: " + role.name();
    }
}