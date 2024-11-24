package com.dentcareplus.dentcareplusspringboot.dto;

import lombok.Data;

@Data
public class MedicalRecordDTO {

    private Long recordID;
    private Long patientId; // Assuming you want to send the patientId instead of the full Patient object
    private String bloodType;
    private Boolean diabetes;
    private Boolean hypertension;
    private Boolean heartDisease;
    private Boolean bleedingDisorders;
    private Boolean osteoporosis;
    private Boolean arthritis;
    private Boolean asthma;
    private Boolean epilepsy;
    private Boolean hivAids;
    private Boolean hepatitis;
    private Boolean thyroidDisorder;
    private Boolean pregnancy;
    private String surgeries;
    private String currentMedications;
    private String drugAllergies;
    private String allergies;
    private String medications;
    private String medicalConditions;
    private String emergencyContactName;
    private String emergencyContactNumber;
}