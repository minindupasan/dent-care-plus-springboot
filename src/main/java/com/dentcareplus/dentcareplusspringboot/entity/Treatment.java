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

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "appointment_id", nullable = false, unique = true)
    @JsonIgnoreProperties({"treatment", "patient"})
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

    public Long getAppointmentID() {
        return this.appointment != null ? this.appointment.getAppointmentID() : null;
    }
}