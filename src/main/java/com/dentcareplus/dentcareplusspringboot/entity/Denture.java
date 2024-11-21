package com.dentcareplus.dentcareplusspringboot.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Table(name = "denture_record")
@Data
public class Denture {

    @Id
    @SequenceGenerator(
            name = "denture_sequence",
            sequenceName = "denture_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "denture_sequence"
    )
    @Column(name = "denture_id")
    private Long dentureId; // Primary Key

    // Relationship with Patient
    @ManyToOne
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;

    // Denture Details
    @Column(name = "denture_type", nullable = false)
    private String dentureType;

    @Column(name = "material_type", nullable = false)
    private String materialType; // e.g., Acrylic, Metal

    @Column(name = "trial_denture_date")
    private LocalDate trialDentureDate;

    @Column(name = "estimated_delivery_date")
    private LocalDate estimatedDeliveryDate;

    @Column(name = "received_date")
    private LocalDate receivedDate; // Nullable

    @Column(name = "delivery_status")
    private String deliveryStatus; // Nullable

    @Column(name = "remarks", length = 500)
    private String remarks;

    @Column(name = "cost")
    private Double cost;

    @Column(name = "payment_status")
    private String paymentStatus; // Nullable

    @Column(name = "lab_name", nullable = false)
    private String labName;

    @Column(name = "ordered_date")
    private LocalDate orderedDate; // Nullable
}