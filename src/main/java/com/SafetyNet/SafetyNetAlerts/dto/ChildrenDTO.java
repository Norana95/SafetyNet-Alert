package com.SafetyNet.SafetyNetAlerts.dto;

import com.SafetyNet.SafetyNetAlerts.model.Person;

import java.util.List;

public class ChildrenDTO {
    public String firstName;
    public String lastName;
    public int age;
    public List<Person> foyer;

    public ChildrenDTO(String firstName, String lastName, int age, List<Person> foyer) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.foyer = foyer;
    }

    public ChildrenDTO() {
    }

    public ChildrenDTO(String firstName, String lastName, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<Person> getFoyer() {
        return foyer;
    }

    public void setFoyer(List<Person> foyer) {
        this.foyer = foyer;
    }
}
