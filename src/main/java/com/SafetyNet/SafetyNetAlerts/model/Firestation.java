package com.SafetyNet.SafetyNetAlerts.model;

public class Firestation {

    public String address;
    public String station;

    public Firestation() {}

    public Firestation(String address, String station) {

        this.address = address;
        this.station = station;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getStation() {
        return station;
    }

    public void setStation(String station) {
        this.station = station;
    }

}
