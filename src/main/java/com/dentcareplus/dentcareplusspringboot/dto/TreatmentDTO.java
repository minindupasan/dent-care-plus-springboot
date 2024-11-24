package com.dentcareplus.dentcareplusspringboot.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class TreatmentDTO {

    private Long treatmentID;
    private Long appointmentID;
    private String treatmentType;
    private LocalDate startDate;
    private LocalDate endDate;
    private Double totalPaid;
    private Double dueAmount;
    private String paymentStatus;
    private String treatmentStatus;
    private String notes;
    private PatientDTO patient;  // Include patient details

    public TreatmentDTO(Long treatmentID, String treatmentType, LocalDate startDate, LocalDate endDate,
                        Double totalPaid, Double dueAmount, String paymentStatus, String treatmentStatus,
                        String notes, Long appointmentID, PatientDTO patient) {
        this.treatmentID = treatmentID;
        this.treatmentType = treatmentType;
        this.startDate = startDate;
        this.endDate = endDate;
        this.totalPaid = totalPaid;
        this.dueAmount = dueAmount;
        this.paymentStatus = paymentStatus;
        this.treatmentStatus = treatmentStatus;
        this.notes = notes;
        this.appointmentID = appointmentID;
        this.patient = patient;
    }
}