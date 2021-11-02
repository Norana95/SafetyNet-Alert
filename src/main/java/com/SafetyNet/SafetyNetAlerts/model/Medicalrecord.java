package com.SafetyNet.SafetyNetAlerts.model;

import lombok.Data;

import java.util.List;

@Data
public class Medicalrecord {
    public String firstName;
    public String lastName;
    public String birthdate;
    public List<String> medications;
    public List<String> allergies;
}
