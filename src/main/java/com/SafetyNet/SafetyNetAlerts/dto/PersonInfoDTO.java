package com.SafetyNet.SafetyNetAlerts.dto;

public class PersonInfoDTO {

    public String lastName;
    public String address;
    public int age;
    public String addressMail;
    public String[] medications;
    public String[] allergies;

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAddressMail() {
        return addressMail;
    }

    public void setAddressMail(String addressMail) {
        this.addressMail = addressMail;
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
