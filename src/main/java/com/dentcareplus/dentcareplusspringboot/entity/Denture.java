package com.dentcareplus.dentcareplusspringboot.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
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
    private Long dentureId; // Primary Key

    // Relationship with Patient
    @ManyToOne
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;

    // Denture Details
    @Column(nullable = false)
    @NotBlank(message = "Denture type is required")
    private String dentureType;

    @Column(nullable = false)
    @NotBlank(message = "Material type is required")
    private String materialType; // e.g., Acrylic, Metal

    @PastOrPresent(message = "Trial denture date cannot be in the future")
    private LocalDate trialDentureDate;

    @Future(message = "Estimated delivery date must be in the future")
    private LocalDate estimatedDeliveryDate;

    @PastOrPresent(message = "Received date cannot be in the future")
    private LocalDate receivedDate;

    @Column(nullable = false)
    @NotBlank(message = "Delivery status is required")
    @Pattern(regexp = "^(In Progress|Delivered|Pending|Canceled)$",
            message = "Invalid delivery status")
    private String deliveryStatus;

    @Column(length = 500)
    @Size(max = 500, message = "Remarks cannot exceed 500 characters")
    private String remarks;

    @Min(value = 0, message = "Cost cannot be negative")
    private Double cost;

    @Column(nullable = false)
    @NotBlank(message = "Payment status is required")
    @Pattern(regexp = "^(Paid|Pending)$", message = "Invalid payment status")
    private String paymentStatus;

    @NotBlank(message = "Lab name is required")
    private String labName;

    @PastOrPresent(message = "Ordered date cannot be in the future")
    private LocalDate orderedDate;
}