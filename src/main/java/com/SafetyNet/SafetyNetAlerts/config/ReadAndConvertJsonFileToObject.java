package com.SafetyNet.SafetyNetAlerts.config;


import com.SafetyNet.SafetyNetAlerts.model.Firestation;
import com.SafetyNet.SafetyNetAlerts.model.Medicalrecord;
import com.SafetyNet.SafetyNetAlerts.model.Person;
import com.SafetyNet.SafetyNetAlerts.service.DataService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class ReadAndConvertJsonFileToObject {

    private final String jsonFilePersons = "src/main/resources/jsonfile/persons.json";
    private final String jsonFileMedicalrecords = "src/main/resources/jsonfile/medicalrecords.json";
    private final String jsonFileFirestations = "src/main/resources/jsonfile/firestations.json";

    @Autowired
    DataService dataService;

    @Bean
    CommandLineRunner commandLineRunner() {

        return args -> {
            initData();
        };
    }
    public void initData() throws IOException {

        dataService.setPersons(new ArrayList<>());
        dataService.setMedicalrecords(new ArrayList<>());
        dataService.setFirestations(new ArrayList<>());
        ObjectMapper mapper = new ObjectMapper();

        Person[] readJsonFilePerson = mapper.readValue(new File(jsonFilePersons), Person[].class);
        Medicalrecord[] readJsonFilemedicalrecords = mapper.readValue(new File(jsonFileMedicalrecords), Medicalrecord[].class);
        Firestation[] readJsonFilefirestations = mapper.readValue(new File(jsonFileFirestations), Firestation[].class);

        dataService.getPersons().addAll(List.of(readJsonFilePerson));
        dataService.getMedicalrecords().addAll(List.of(readJsonFilemedicalrecords));
        dataService.getFirestations().addAll(List.of(readJsonFilefirestations));
    }
}

