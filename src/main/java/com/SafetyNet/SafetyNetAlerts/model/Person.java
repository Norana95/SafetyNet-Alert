package com.SafetyNet.SafetyNetAlerts.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table
public class Person {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    public String firstName;
    public String lastName;
    public String address;
    public String city;
    public String zip;
    public String phone;
    public String email;

    public Long getId() {
        return id;
    }
    public Person() { }

    public Person(Long u_id, String firstName, String lastName, String address, String city, String zip, String phone, String email) {
        id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.city = city;
        this.zip = zip;
        this.phone = phone;
        this.email = email;
    }
}
