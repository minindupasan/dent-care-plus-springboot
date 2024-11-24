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
    private String notes;
    
    public TreatmentDTO(Long treatmentID, String treatmentType, LocalDate startDate, LocalDate endDate, Double totalPaid, Double dueAmount, String notes, Long appointmentID) {
        this.treatmentID = treatmentID;
        this.appointmentID = appointmentID;
        this.treatmentType = treatmentType;
        this.startDate = startDate;
        this.endDate = endDate;
        this.totalPaid = totalPaid;
        this.dueAmount = dueAmount;
        this.notes = notes;
    }
}