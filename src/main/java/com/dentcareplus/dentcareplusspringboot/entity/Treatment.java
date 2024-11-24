package com.dentcareplus.dentcareplusspringboot.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Table(name = "treatment")
@Data
public class Treatment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long treatmentID;

    @ManyToOne
    @JoinColumn(name = "appointment_id", nullable = false)
    @JsonIgnoreProperties({"treatment", "patient"})
    // Avoid infinite recursion and avoid sending full Appointment object
    private Appointment appointment;

    @Column(name = "treatment_type", nullable = false)
    private String treatmentType;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @Column(name = "total_paid")
    private Double totalPaid;

    @Column(name = "due_amount")
    private Double dueAmount;

    @Column(name = "notes", length = 500)
    private String notes;

    // Getter for appointment ID
    public Long getAppointmentID() {
        return this.appointment != null ? this.appointment.getAppointmentID() : null;
    }
}