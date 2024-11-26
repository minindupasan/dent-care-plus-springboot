package com.dentcareplus.dentcareplusspringboot.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class DentureDTO {
    private Long dentureID;
    private String dentureType;
    private String materialType;
    private LocalDate trialDentureDate;
    private LocalDate estimatedDeliveryDate;
    private LocalDate receivedDate;
    private String remarks;
    private Double cost;
    private String paymentStatus;
    private String deliveryStatus;
    private String labName;
    private LocalDate orderedDate;

    // Include PatientDTO to capture patient details
    private PatientDTO patient;

    // Constructor
    public DentureDTO() {
        this.dentureID = dentureID;
        this.dentureType = dentureType;
        this.materialType = materialType;
        this.trialDentureDate = trialDentureDate;
        this.estimatedDeliveryDate = estimatedDeliveryDate;
        this.receivedDate = receivedDate;
        this.remarks = remarks;
        this.cost = cost;
        this.paymentStatus = paymentStatus;
        this.deliveryStatus = deliveryStatus;
        this.labName = labName;
        this.orderedDate = orderedDate;
        this.patient = patient;
    }
}