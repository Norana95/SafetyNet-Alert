package com.SafetyNet.SafetyNetAlerts.dto;

import com.SafetyNet.SafetyNetAlerts.model.Views;
import com.fasterxml.jackson.annotation.JsonView;

import java.util.List;

public class FloodDTO {
    String address;
    @JsonView(Views.Normal.class)
    List<FireDTO> residents;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<FireDTO> getResidents() {
        return residents;
    }

    public void setResidents(List<FireDTO> residents) {
        this.residents = residents;
    }
}
