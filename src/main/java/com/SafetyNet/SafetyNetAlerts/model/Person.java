package com.SafetyNet.SafetyNetAlerts.model;

import com.fasterxml.jackson.annotation.JsonView;

public class Person {

    @JsonView(Views.Normal.class)
    public String firstName;
    @JsonView(Views.Normal.class)
    public String lastName;
    @JsonView(Views.WithPhoneAndAddress.class)
    public String address;
    public String city;
    public String zip;
    @JsonView(Views.WithPhoneAndAddress.class)
    public String phone;
    public String email;

    public Person() {
    }

    public Person(String firstName, String lastName, String address, String city, String zip, String phone, String email) {

        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.city = city;
        this.zip = zip;
        this.phone = phone;
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCity() {
        return city;
    }

    public String getZip() {
        return zip;
    }

    public Person(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
