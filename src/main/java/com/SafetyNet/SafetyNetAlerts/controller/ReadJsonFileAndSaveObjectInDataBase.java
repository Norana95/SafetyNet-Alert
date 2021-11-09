package com.SafetyNet.SafetyNetAlerts.controller;


import com.SafetyNet.SafetyNetAlerts.model.Person;
import com.SafetyNet.SafetyNetAlerts.repository.PersonRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.File;
import java.util.List;

@Configuration
public class ReadJsonFileAndSaveObjectInDataBase {

    private final String jsonFilePersons = "src/main/resources/jsonfile/persons.json";


    @Bean
    CommandLineRunner commandLineRunner(PersonRepository personRepository) {
        //il fallait rajouter un constructeur vide, pourquoi ?
        // il fallait modifier la list de person en []
        // ajouter un id qui s'increment automatiquement par la base de donnée
        // pourquoi l'application ne s'arrete pas quand les personnes sont sauvegarder???
        return args -> {
            ObjectMapper mapper = new ObjectMapper();
            Person[] persons = mapper.readValue(new File(jsonFilePersons), Person[].class);
            personRepository.saveAll(List.of(persons));
            System.out.println("persons saved !");
        };
    }
}

