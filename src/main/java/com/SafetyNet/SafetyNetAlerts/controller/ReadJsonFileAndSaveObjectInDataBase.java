package com.SafetyNet.SafetyNetAlerts.controller;


import com.SafetyNet.SafetyNetAlerts.model.Firestation;
import com.SafetyNet.SafetyNetAlerts.model.Medicalrecord;
import com.SafetyNet.SafetyNetAlerts.model.Person;
import com.SafetyNet.SafetyNetAlerts.repository.FirestationRepository;
import com.SafetyNet.SafetyNetAlerts.repository.MedicalrecordRepository;
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
    private final String jsonFileMedicalrecords = "src/main/resources/jsonfile/medicalrecords.json";
    private final String jsonFileFirestations = "src/main/resources/jsonfile/firestations.json";

    @Bean
    CommandLineRunner commandLineRunner(PersonRepository personRepository, MedicalrecordRepository medicalrecordRepository, FirestationRepository firestationRepository) {

        return args -> {
            ObjectMapper mapper = new ObjectMapper();

            Person[] persons = mapper.readValue(new File(jsonFilePersons), Person[].class);
            Medicalrecord[] medicalrecords = mapper.readValue(new File(jsonFileMedicalrecords), Medicalrecord[].class);
            Firestation[] firestations = mapper.readValue(new File(jsonFileFirestations), Firestation[].class);

            personRepository.saveAll(List.of(persons));
            medicalrecordRepository.saveAll(List.of(medicalrecords));
            firestationRepository.saveAll(List.of(firestations));

            System.out.println("data persons saved !");
            System.out.println("data medical records saved !");
            System.out.println("data firestations saved !");
        };
    }
}

