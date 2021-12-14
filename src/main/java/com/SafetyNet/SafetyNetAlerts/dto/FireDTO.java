package com.SafetyNet.SafetyNetAlerts.dto;

import com.SafetyNet.SafetyNetAlerts.model.Views;
import com.fasterxml.jackson.annotation.JsonView;

public class FireDTO {
    @JsonView(Views.Normal.class)
    public String lastName;
    @JsonView(Views.Normal.class)
    public String phoneNumber;
    @JsonView(Views.Normal.class)
    public int age;
    @JsonView(Views.Normal.class)
    public String[] medications;
    @JsonView(Views.Normal.class)
    public String[] allergies;
    public String stationNumber;

    public FireDTO() {
    }

    public String getStationNumber() {
        return stationNumber;
    }

    public void setStationNumber(String stationNumber) {
        this.stationNumber = stationNumber;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String[] getMedications() {
        return medications;
    }

    public void setMedications(String[] medications) {
        this.medications = medications;
    }

    public String[] getAllergies() {
        return allergies;
    }

    public void setAllergies(String[] allergies) {
        this.allergies = allergies;
    }
}
