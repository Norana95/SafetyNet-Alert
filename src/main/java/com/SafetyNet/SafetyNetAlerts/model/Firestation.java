package com.SafetyNet.SafetyNetAlerts.model;

import lombok.Data;

import javax.persistence.*;

@Table
@Data
@Entity
public class Firestation {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    public String address;
    public String station;

    public Firestation() {}

    public Firestation(long id, String address, String station) {
        this.id = id;
        this.address = address;
        this.station = station;
    }
}
