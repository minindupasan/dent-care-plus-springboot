package com.dentcareplus.dentcareplusspringboot.dto;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class AppointmentDTO {

    private Long appointmentID;
    private PatientDTO patient; // Include full patient details
    private LocalDate appointmentDate;
    private LocalTime appointmentTime;
    private String reason;
    private String status;

    // Constructor
    public AppointmentDTO(Long appointmentID, PatientDTO patient, LocalDate appointmentDate, LocalTime appointmentTime, String reason, String status) {
        this.appointmentID = appointmentID;
        this.patient = patient;
        this.appointmentDate = appointmentDate;
        this.appointmentTime = appointmentTime;
        this.reason = reason;
        this.status = status;
    }
}