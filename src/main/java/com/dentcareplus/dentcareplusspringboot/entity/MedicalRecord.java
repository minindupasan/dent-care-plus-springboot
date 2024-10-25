package com.dentcareplus.dentcareplusspringboot.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "medical_record")
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
    @JoinColumn(name = "patient_id", nullable = false)
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

    public Long getRecordID() {
        return recordID;
    }

    public void setRecordID(Long recordID) {
        this.recordID = recordID;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public String getBloodType() {
        return bloodType;
    }

    public void setBloodType(String bloodType) {
        this.bloodType = bloodType;
    }

    public Boolean getDiabetes() {
        return diabetes;
    }

    public void setDiabetes(Boolean diabetes) {
        this.diabetes = diabetes;
    }

    public Boolean getHypertension() {
        return hypertension;
    }

    public void setHypertension(Boolean hypertension) {
        this.hypertension = hypertension;
    }

    public Boolean getHeartDisease() {
        return heartDisease;
    }

    public void setHeartDisease(Boolean heartDisease) {
        this.heartDisease = heartDisease;
    }

    public Boolean getBleedingDisorders() {
        return bleedingDisorders;
    }

    public void setBleedingDisorders(Boolean bleedingDisorders) {
        this.bleedingDisorders = bleedingDisorders;
    }

    public Boolean getOsteoporosis() {
        return osteoporosis;
    }

    public void setOsteoporosis(Boolean osteoporosis) {
        this.osteoporosis = osteoporosis;
    }

    public Boolean getArthritis() {
        return arthritis;
    }

    public void setArthritis(Boolean arthritis) {
        this.arthritis = arthritis;
    }

    public Boolean getAsthma() {
        return asthma;
    }

    public void setAsthma(Boolean asthma) {
        this.asthma = asthma;
    }

    public Boolean getEpilepsy() {
        return epilepsy;
    }

    public void setEpilepsy(Boolean epilepsy) {
        this.epilepsy = epilepsy;
    }

    public Boolean getHivAids() {
        return hivAids;
    }

    public void setHivAids(Boolean hivAids) {
        this.hivAids = hivAids;
    }

    public Boolean getHepatitis() {
        return hepatitis;
    }

    public void setHepatitis(Boolean hepatitis) {
        this.hepatitis = hepatitis;
    }

    public Boolean getThyroidDisorder() {
        return thyroidDisorder;
    }

    public void setThyroidDisorder(Boolean thyroidDisorder) {
        this.thyroidDisorder = thyroidDisorder;
    }

    public Boolean getPregnancy() {
        return pregnancy;
    }

    public void setPregnancy(Boolean pregnancy) {
        this.pregnancy = pregnancy;
    }

    public String getSurgeries() {
        return surgeries;
    }

    public void setSurgeries(String surgeries) {
        this.surgeries = surgeries;
    }

    public String getCurrentMedications() {
        return currentMedications;
    }

    public void setCurrentMedications(String currentMedications) {
        this.currentMedications = currentMedications;
    }

    public String getDrugAllergies() {
        return drugAllergies;
    }

    public void setDrugAllergies(String drugAllergies) {
        this.drugAllergies = drugAllergies;
    }

    public String getAllergies() {
        return allergies;
    }

    public void setAllergies(String allergies) {
        this.allergies = allergies;
    }

    public String getMedications() {
        return medications;
    }

    public void setMedications(String medications) {
        this.medications = medications;
    }

    public String getMedicalConditions() {
        return medicalConditions;
    }

    public void setMedicalConditions(String medicalConditions) {
        this.medicalConditions = medicalConditions;
    }

    public String getEmergencyContactName() {
        return emergencyContactName;
    }

    public void setEmergencyContactName(String emergencyContactName) {
        this.emergencyContactName = emergencyContactName;
    }

    public String getEmergencyContactNumber() {
        return emergencyContactNumber;
    }

    public void setEmergencyContactNumber(String emergencyContactNumber) {
        this.emergencyContactNumber = emergencyContactNumber;
    }
}
