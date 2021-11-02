package com.SafetyNet.SafetyNetAlerts.model;

import lombok.Data;

import javax.persistence.Table;

@Table(name="firestations")
@Data
public class Firestation {

    public String address;
    public String station;

}
