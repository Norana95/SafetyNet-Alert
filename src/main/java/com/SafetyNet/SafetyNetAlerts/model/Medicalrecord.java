package com.SafetyNet.SafetyNetAlerts.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Table
@Entity
public class Medicalrecord {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public String firstName;
    public String lastName;
    public String birthdate;
    public String[] medications;
    public String[] allergies;

    public Medicalrecord() {
    }


    public Long getId() {
        return id;
    }

    public Medicalrecord(Long id, String firstName, String lastName, String birthdate, String[] medications, String[] allergies) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthdate = birthdate;
        this.medications = medications;
        this.allergies = allergies;
    }
}
