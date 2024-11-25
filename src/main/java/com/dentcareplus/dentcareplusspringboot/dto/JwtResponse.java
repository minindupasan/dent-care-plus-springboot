package com.dentcareplus.dentcareplusspringboot.dto;

import lombok.Data;

@Data
public class JwtResponse {
    private String jwtToken;
    private String role;

    public JwtResponse(String jwtToken, String role) {
        this.jwtToken = jwtToken;
        this.role = role;
    }
}