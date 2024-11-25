package com.dentcareplus.dentcareplusspringboot.controller;

import com.dentcareplus.dentcareplusspringboot.config.JwtUtils;
import com.dentcareplus.dentcareplusspringboot.dto.JwtRequest;
import com.dentcareplus.dentcareplusspringboot.dto.JwtResponse;
import com.dentcareplus.dentcareplusspringboot.enums.RoleEnum;
import com.dentcareplus.dentcareplusspringboot.service.AuthService;
import com.dentcareplus.dentcareplusspringboot.service.UserDetailsServiceImpl;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;
    private final UserDetailsServiceImpl userDetailsService;
    private final AuthService authService;

    public AuthenticationController(AuthenticationManager authenticationManager, JwtUtils jwtUtils, UserDetailsServiceImpl userDetailsService, AuthService authService) {
        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;
        this.userDetailsService = userDetailsService;
        this.authService = authService;
    }

    @PostMapping("/login")
    public JwtResponse login(@RequestBody JwtRequest request) {
        // Authenticate the user
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );

        var userDetails = userDetailsService.loadUserByUsername(request.getUsername());

        String token = jwtUtils.generateToken(userDetails);

        String userRole = userDetails.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("User role not found"));

        return new JwtResponse(token, userRole);
    }

    @PostMapping("/register")
    public JwtResponse register(@RequestBody JwtRequest request, @RequestParam RoleEnum role) {
        authService.registerUser(request.getUsername(), request.getPassword(), role);

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );
        var userDetails = userDetailsService.loadUserByUsername(request.getUsername());
        String token = jwtUtils.generateToken(userDetails);

        String userRole = userDetails.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .findFirst() // Get the first role (or the only role)
                .orElseThrow(() -> new RuntimeException("User role not found"));

        return new JwtResponse(token, userRole);
    }
}