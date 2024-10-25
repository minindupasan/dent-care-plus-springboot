package com.dentcareplus.dentcareplusspringboot.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "patient", uniqueConstraints = {
        @UniqueConstraint(name = "email_unique", columnNames = "email")
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

    @Column(name = "first_name", length = 50, nullable = false)
    private String firstName;

    @Column(name = "last_name", length = 50, nullable = false)
    private String lastName;

    @Column(name = "email", length = 100, nullable = false)
    private String email;

    @Column(name = "contact_no", length = 12, nullable = false)
    private String contactNo;

    @Column(name = "gender", length = 20, nullable = false)
    private String gender;

    @Column(name = "medical_records", columnDefinition = "TEXT")
    private String medicalRecords;

    @Column(name = "dob", nullable = false)
    private LocalDate dob;

    @Column(name = "created_date", nullable = false)
    private LocalDate createdDate;

    @PrePersist
    protected void onCreate() {
        if (this.createdDate == null) {
            this.createdDate = LocalDate.now();
        }
    }

    // Getters and Setters
    public Long getPatientID() {
        return patientID;
    }

    public void setPatientID(Long patientID) {
        this.patientID = patientID;
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