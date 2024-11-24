package com.dentcareplus.dentcareplusspringboot.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class PrescriptionDTO {

    private Long prescriptionId;  // Prescription ID

    private Long appointmentId;  // ID of the linked appointment

    private LocalDate dateIssued;  // Date when the prescription was issued

    private String notes;  // Additional notes for the prescription

    private List<MedicationDTO> medications;  // List of medications

    @Data
    public static class MedicationDTO {
        private String medicationName;  // Name of the medication
        private String dosage;  // Dosage details (e.g., "500 mg")
        private int frequency;  // Frequency of intake per day
        private int duration;  // Duration in days

        private String frequencyDisplay;  // Readable format for frequency
        private String durationDisplay;  // Readable format for duration
    }
}