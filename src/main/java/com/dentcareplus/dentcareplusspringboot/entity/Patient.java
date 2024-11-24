package com.dentcareplus.dentcareplusspringboot.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "patient", uniqueConstraints = {
        @UniqueConstraint(name = "email_unique", columnNames = "email")
})
@Data
@NoArgsConstructor
public class Patient {
    @Id
    @SequenceGenerator(
            name = "patient_sequence",
            sequenceName = "patient_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "patient_sequence"
    )
    private Long patientID;

    @NotBlank(message = "First name is required")
    @Column(name = "first_name", length = 50, nullable = false)
    private String firstName;

    @NotBlank(message = "Last name is required")
    @Column(name = "last_name", length = 50, nullable = false)
    private String lastName;

    @Email(message = "Invalid email format")
    @NotBlank(message = "Email is required")
    @Column(name = "email", length = 100, nullable = false)
    private String email;

    @NotBlank(message = "Contact number is required")
    @Pattern(regexp = "\\d{10,12}", message = "Contact number must be between 10 and 12 digits")
    @Column(name = "contact_no", length = 12, nullable = false)
    private String contactNo;

    @NotBlank(message = "Gender is required")
    @Column(name = "gender", length = 20, nullable = false)
    private String gender;

    @Past(message = "Date of birth must be in the past")
    @Column(name = "dob", nullable = false)
    private LocalDate dob;

    @Column(name = "created_date", nullable = false)
    private LocalDate createdDate;

    // Constructor to initialize createdDate
    public Patient(String firstName, String lastName, String email, String contactNo, String gender, LocalDate dob) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.contactNo = contactNo;
        this.gender = gender;
        this.dob = dob;
        this.createdDate = LocalDate.now(); // Set current date on creation
    }
}