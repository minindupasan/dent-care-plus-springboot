package com.dentcareplus.dentcareplusspringboot.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@Entity
public class Prescription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long prescriptionId;

    @OneToOne
    @JoinColumn(name = "appointment_id", nullable = false)
    private Appointment appointment;

    @Column(name = "date_issued", nullable = false)
    private LocalDate dateIssued;

    @Column(length = 500)
    private String notes;

    @ElementCollection
    @CollectionTable(name = "medication", joinColumns = @JoinColumn(name = "prescription_id"))
    private List<Medication> medications;

    @Embeddable
    @Data
    @Table(name = "medication")
    public static class Medication {

        @Column(name = "medication_name", nullable = false)
        private String medicationName;

        @Column(name = "dosage", nullable = false)
        private String dosage;

        @Column(name = "frequency", nullable = false)
        private int frequency;

        @Column(name = "duration", nullable = false)
        private int duration;

        public String getDurationDisplay() {
            return duration + " days";
        }

        public String getFrequencyDisplay() {
            return frequency + " times per day";
        }
    }
}