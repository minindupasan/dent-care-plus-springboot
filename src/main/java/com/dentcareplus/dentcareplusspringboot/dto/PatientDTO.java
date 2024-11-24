package com.dentcareplus.dentcareplusspringboot.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class PatientDTO {
    private Long patientID;
    private String firstName;
    private String lastName;
    private String email;
    private String contactNo;
    private String gender;
    private LocalDate dob;
    private LocalDate createdDate;

    // Constructor
    public PatientDTO(Long patientID, String firstName, String lastName, String email, String contactNo, String gender, LocalDate dob, LocalDate createdDate) {
        this.patientID = patientID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.contactNo = contactNo;
        this.gender = gender;
        this.dob = dob;
        this.createdDate = createdDate;
    }
}