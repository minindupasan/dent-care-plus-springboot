package com.dentcareplus.dentcareplusspringboot.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "patient", uniqueConstraints = {
        @UniqueConstraint(name = "email_unique", columnNames = "email"),
        @UniqueConstraint(name = "contact_number_unique", columnNames = "contactNo"),
        @UniqueConstraint(name = "formatted_patient_id_unique", columnNames = "formattedPatientID")
})
public class Patient {
    @Id
    @SequenceGenerator(
            name = "patient_sequence",
            sequenceName = "patient_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "patient_sequence"
    )
    private Long patientID;

    @Column(name = "formattedPatientID", length = 10)
    private String formattedPatientID;

    @Column(name = "firstName", length = 50, nullable = false)
    private String firstName;

    @Column(name = "lastName", length = 50, nullable = false)
    private String lastName;

    @Column(name = "email", length = 100, nullable = false)
    private String email;

    @Column(name = "contactNo", length = 12, nullable = false)
    private String contactNo;

    @Column(name = "gender", length = 20, nullable = false)
    private String gender;

    @Column(name = "medicalRecords", columnDefinition = "TEXT")
    private String medicalRecords;

    @Column(name = "dob", nullable = false)
    private LocalDate dob;

    @Column(name = "createdDate", columnDefinition = "DATE DEFAULT CURRENT_DATE")
    private LocalDate createdDate = LocalDate.now();

    // Automatically generate formattedPatientID after saving to the database
    @PostPersist
    private void generateFormattedPatientID() {
        this.formattedPatientID = String.format("P%04d", this.patientID);
    }

    // Getters and Setters
    public Long getPatientID() {
        return patientID;
    }

    public void setPatientID(Long patientID) {
        this.patientID = patientID;
    }

    public String getFormattedPatientID() {
        return formattedPatientID;
    }

    public void setFormattedPatientID(String formattedPatientID) {
        this.formattedPatientID = formattedPatientID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getMedicalRecords() {
        return medicalRecords;
    }

    public void setMedicalRecords(String medicalRecords) {
        this.medicalRecords = medicalRecords;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }
}
