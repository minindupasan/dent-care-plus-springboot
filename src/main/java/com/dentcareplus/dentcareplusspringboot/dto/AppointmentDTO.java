package com.dentcareplus.dentcareplusspringboot.dto;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class AppointmentDTO {

    private Long appointmentID;
    private Long patientID;
    private String firstName;
    private String lastName;
    private LocalDate appointmentDate;
    private LocalTime appointmentTime;
    private String reason;
    private String status;

    // Constructor
    public AppointmentDTO(Long appointmentID, Long patientID, String firstName, String lastName, LocalDate appointmentDate, LocalTime appointmentTime, String reason, String status) {
        this.appointmentID = appointmentID;
        this.patientID = patientID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.appointmentDate = appointmentDate;
        this.appointmentTime = appointmentTime;
        this.reason = reason;
        this.status = status;
    }
}