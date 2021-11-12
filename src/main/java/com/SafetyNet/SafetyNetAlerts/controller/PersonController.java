package com.SafetyNet.SafetyNetAlerts.controller;

import com.SafetyNet.SafetyNetAlerts.model.Person;
import com.SafetyNet.SafetyNetAlerts.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//la classe controller fait appelle à la classe service
@RestController
@RequestMapping("/persons")
public class PersonController {

    PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping
    public Person createPerson(@RequestBody Person person) {
        return personService.savePerson(person);
    }
    @PostMapping("/personsList")
    public Iterable<Person> createPersons(@RequestBody List<Person> persons) {
        return personService.savePersons(persons);
    }


    @GetMapping("/list")
    public Iterable<Person> getPersons() {
        return personService.getPersons();
    }

    @GetMapping
    public Person getPersonById(@RequestBody Person person, @PathVariable Long id) {
        personService.getPerson(person.getId());
        return person;
    }

    @PutMapping
    public Person updatePerson(Person personUpdated) {
        personService.updatePerson(personUpdated);
        return personUpdated;
    }
}
