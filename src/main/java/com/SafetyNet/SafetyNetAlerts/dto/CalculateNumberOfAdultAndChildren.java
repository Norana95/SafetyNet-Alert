package com.SafetyNet.SafetyNetAlerts.dto;

import com.SafetyNet.SafetyNetAlerts.model.Person;
import com.SafetyNet.SafetyNetAlerts.model.Views;
import com.fasterxml.jackson.annotation.JsonView;

import java.util.List;

public class CalculateNumberOfAdultAndChildren {

    public int adult;
    public int children;
    @JsonView(Views.WithPhoneAndAddress.class)
    public List<Person> person;

    public CalculateNumberOfAdultAndChildren(int adult, int children, List<Person> person) {
        this.adult = adult;
        this.children = children;
        this.person = person;
    }

    public CalculateNumberOfAdultAndChildren() {
    }

    public List<Person> getPerson() {
        return person;
    }

    public void setPerson(List<Person> person) {
        this.person = person;
    }

    public int getAdult() {
        return adult;
    }

    public void setAdult(int adult) {
        this.adult = adult;
    }

    public int getChildren() {
        return children;
    }

    public void setChildren(int children) {
        this.children = children;
    }

}
