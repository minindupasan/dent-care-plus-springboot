package com.dentcareplus.dentcareplusspringboot.dto;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class AppointmentDTO {

    private Long appointmentID;
    private Long patientID;
    private LocalDate appointmentDate;
    private LocalTime appointmentTime;
    private String reason;
    private String status;

    // Constructor
    public AppointmentDTO(Long appointmentID, Long patientID, LocalDate appointmentDate, LocalTime appointmentTime, String reason, String status) {
        this.appointmentID = appointmentID;
        this.patientID = patientID;
        this.appointmentDate = appointmentDate;
        this.appointmentTime = appointmentTime;
        this.reason = reason;
        this.status = status;
    }
}