package com.dentcareplus.dentcareplusspringboot.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class DentureDTO {

    @NotNull(message = "Denture type is required")
    private String dentureType;

    @NotNull(message = "Material type is required")
    private String materialType;

    private LocalDate trialDentureDate;
    private LocalDate estimatedDeliveryDate;
    private LocalDate receivedDate;
    private String remarks;
    private Double cost;
    private String paymentStatus = "Pending";
    private String deliveryStatus = "In Progress";
    private String labName;
    private LocalDate orderedDate;

}