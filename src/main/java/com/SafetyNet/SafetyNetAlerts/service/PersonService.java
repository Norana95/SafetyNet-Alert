package com.SafetyNet.SafetyNetAlerts.service;

import com.SafetyNet.SafetyNetAlerts.model.Person;
import com.SafetyNet.SafetyNetAlerts.repository.PersonRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

//service fait appelle à repository
//dans la classe service on retrouve les methodes metier
@Data
@Service
public class PersonService {

    private PersonRepository personRepository;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public Person savePerson(Person person) {
        return personRepository.save(person);
    }

    public Iterable<Person> savePersons(List<Person> persons) {
        return personRepository.saveAll(persons);
    }


    public void deletePerson(Person person) {
        personRepository.deleteById(person.getId());
    }

    public Optional<Person> getPerson(final Long id) {
        return personRepository.findById(id);
    }

    public Iterable<Person> getPersons() {
        return personRepository.findAll();
    }

    public Person updatePerson(Person personUpdated) {
        Person person = personRepository.findById(personUpdated.getId()).orElseThrow(RuntimeException::new);
        personUpdated.setAddress(person.getAddress());
        personUpdated.setCity(person.getCity());
        personUpdated.setEmail(person.getEmail());
        personUpdated.setPhone(person.getPhone());
        personUpdated.setZip(person.getZip());
        return personRepository.save(personUpdated);
    }

}
