package com.dentcareplus.dentcareplusspringboot;

import jakarta.persistence.*;

@Entity
@Table(name = "patient", uniqueConstraints = {
        @UniqueConstraint(name = "email_unique", columnNames = "email")
})
public class Patient {
    @Id
    @SequenceGenerator(
            name = "patient_sequence",
            sequenceName = "patient_sequence",
            allocationSize = 1,
            initialValue = 1000
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "patient_sequence"
    )
    @Column(name = "ID", updatable = false, nullable = false)
    private Long id;
    @Column(name = "FirstName", nullable = false, columnDefinition = "TEXT")
    private String firstName;
    @Column(name = "LastName", nullable = false, columnDefinition = "TEXT")
    private String lastName;
    @Column(name = "DOB ", nullable = false, columnDefinition = "TEXT")
    private String dob;
    @Column(name = "Gender", nullable = false, columnDefinition = "TEXT")
    private String gender;
    @Column(name = "Email", nullable = false, columnDefinition = "TEXT")
    private String email;
    @Column(name = "Contact", nullable = false, columnDefinition = "TEXT")
    private String contact;
    @Column(name = "Address", nullable = false, columnDefinition = "TEXT")
    private String address;

    public Patient(String firstName, String lastName, String dob, String gender, String email, String contact, String address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dob = dob;
        this.gender = gender;
        this.email = email;
        this.contact = contact;
        this.address = address;
    }

    public Patient(Long id, String firstName, String lastName, String dob, String gender, String email, String contact, String address) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dob = dob;
        this.gender = gender;
        this.email = email;
        this.contact = contact;
        this.address = address;
    }

    public Patient() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}

