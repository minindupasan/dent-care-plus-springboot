package com.dentcareplus.dentcareplusspringboot.dto;

import java.time.LocalDate;

public class TreatmentDTO {

    private Long treatmentID;
    private String treatmentType;
    private LocalDate startDate;
    private LocalDate endDate;
    private Double totalPaid;
    private Double dueAmount;
    private String notes;
    private Long appointmentID;  // Only the ID of Appointment to avoid lazy-loading issues

    // Constructor
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

    // Getters and Setters
    public Long getTreatmentID() {
        return treatmentID;
    }

    public void setTreatmentID(Long treatmentID) {
        this.treatmentID = treatmentID;
    }

    public String getTreatmentType() {
        return treatmentType;
    }

    public void setTreatmentType(String treatmentType) {
        this.treatmentType = treatmentType;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public Double getTotalPaid() {
        return totalPaid;
    }

    public void setTotalPaid(Double totalPaid) {
        this.totalPaid = totalPaid;
    }

    public Double getDueAmount() {
        return dueAmount;
    }

    public void setDueAmount(Double dueAmount) {
        this.dueAmount = dueAmount;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Long getAppointmentID() {
        return appointmentID;
    }

    public void setAppointmentID(Long appointmentID) {
        this.appointmentID = appointmentID;
    }
}