package com.dentcareplus.dentcareplusspringboot.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "appointment")
@Data
public class Appointment {

    @Id
    @SequenceGenerator(
            name = "appointment_sequence",
            sequenceName = "appointment_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "appointment_sequence"
    )
    private Long appointmentID;

    @ManyToOne
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;

    @Column(name = "appointment_date", nullable = false)
    @NotNull(message = "Appointment date is required")
    @FutureOrPresent(message = "Appointment date must be today or in the future")
    private LocalDate appointmentDate;

    @Column(name = "appointment_time", nullable = false)
    @NotNull(message = "Appointment time is required")
    private LocalTime appointmentTime;

    @Column(name = "reason", length = 255)
    @Size(max = 255, message = "Reason cannot exceed 255 characters")
    private String reason;

    @Column(name = "status", nullable = false)
    @NotBlank(message = "Status is required")
    @Pattern(regexp = "^(Scheduled|Completed|Canceled)$",
            message = "Invalid status value. Allowed values: Scheduled, Completed, Canceled")
    private String status = "Scheduled";
}