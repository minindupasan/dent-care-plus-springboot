package com.dentcareplus.dentcareplusspringboot.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "medical_record")
@Data
public class MedicalRecord {
    @Id
    @SequenceGenerator(
            name = "medical_record_sequence",
            sequenceName = "medical_record_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "medical_record_sequence"
    )
    private Long recordID;

    @OneToOne
    @JoinColumn(name = "patient_id", nullable = false, unique = true)
    private Patient patient;

    @Column(name = "blood_type", nullable = false)
    private String bloodType;

    @Column(name = "diabetes", nullable = false)
    private Boolean diabetes;

    @Column(name = "hypertension", nullable = false)
    private Boolean hypertension;

    @Column(name = "heart_disease", nullable = false)
    private Boolean heartDisease;

    @Column(name = "bleeding_disorders", nullable = false)
    private Boolean bleedingDisorders;

    @Column(name = "osteoporosis", nullable = false)
    private Boolean osteoporosis;

    @Column(name = "arthritis", nullable = false)
    private Boolean arthritis;

    @Column(name = "asthma", nullable = false)
    private Boolean asthma;

    @Column(name = "epilepsy", nullable = false)
    private Boolean epilepsy;

    @Column(name = "hiv_aids", nullable = false)
    private Boolean hivAids;

    @Column(name = "hepatitis", nullable = false)
    private Boolean hepatitis;

    @Column(name = "thyroid_disorder", nullable = false)
    private Boolean thyroidDisorder;

    @Column(name = "pregnancy", nullable = true)
    private Boolean pregnancy;

    @Column(name = "surgeries", columnDefinition = "TEXT")
    private String surgeries;

    @Column(name = "current_medications", columnDefinition = "TEXT")
    private String currentMedications;

    @Column(name = "drug_allergies", columnDefinition = "TEXT")
    private String drugAllergies;

    @Column(name = "allergies", nullable = false)
    private String allergies;

    @Column(name = "medications", nullable = false)
    private String medications;

    @Column(name = "medical_conditions", nullable = false)
    private String medicalConditions;

    @Column(name = "emergency_contact_name", nullable = false)
    private String emergencyContactName;

    @Column(name = "emergency_contact_number", nullable = false)
    private String emergencyContactNumber;
}
