package com.dentcareplus.dentcareplusspringboot.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthCheckResponse {
    private String username;
    private String role;
}