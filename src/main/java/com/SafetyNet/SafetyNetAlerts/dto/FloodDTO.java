package com.SafetyNet.SafetyNetAlerts.dto;

import java.util.List;

public class FloodDTO {

    public String address;
    public List<Foyer> foyer;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Foyer> getFoyer() {
        return foyer;
    }

    public void setFoyer(List<Foyer> foyer) {
        this.foyer = foyer;
    }
}
