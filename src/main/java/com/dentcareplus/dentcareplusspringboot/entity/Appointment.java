package com.dentcareplus.dentcareplusspringboot.entity;

import jakarta.persistence.*;
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
    @Column(name = "appointment_id")
    private Long appointmentID;

    @ManyToOne
    @JoinColumn(name = "patient_id", nullable = false)  // Removed CascadeType.ALL
    private Patient patient;

    @Column(name = "appointment_date", nullable = false)
    private LocalDate appointmentDate;

    @Column(name = "appointment_time", nullable = false)
    private LocalTime appointmentTime;

    @Column(name = "reason", length = 255)
    private String reason;

    @Column(name = "status", nullable = false)
    private String status = "Scheduled";

    @OneToOne(mappedBy = "appointment", cascade = CascadeType.ALL, orphanRemoval = true)
    private Treatment treatment;
}