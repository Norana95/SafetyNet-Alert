package com.SafetyNet.SafetyNetAlerts.ControllerUnitTest;

import com.SafetyNet.SafetyNetAlerts.controller.PersonController;
import com.SafetyNet.SafetyNetAlerts.model.Person;
import com.SafetyNet.SafetyNetAlerts.service.PersonService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = PersonController.class)
public class PersonControllerTest {


    @Autowired
    MockMvc mockMvc;

    @MockBean
    PersonService personService;

    @Autowired
    ObjectMapper objectMapper;

    @InjectMocks
    PersonController personController;

    //ajouter une personne
    @Test
    void createPersonTest() throws Exception {
        Person person = new Person("Norhene", "Boublenza", "54 rue jonquoy", "Paris", "54878", "2155454551", "boublenza.n@gmail.com");
        String jsonPerson = objectMapper.writeValueAsString(person);
        mockMvc.perform(MockMvcRequestBuilders.post("/create").accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON).content(jsonPerson)).andExpect(status().isOk());
    }

    ////mettre à jour une personne existante
    @Test
    void updatePersonTest() throws Exception {
        Person person = new Person("Norhene", "Boublenza");
        String jsonPerson = objectMapper.writeValueAsString(person);
        mockMvc.perform(patch("/person/update").param("firstName", "John").param("lastName", "Boyd").contentType(MediaType.APPLICATION_JSON).content(jsonPerson)).andExpect(status().isOk());
    }

    // supprimer une personne
    @Test
    void deletePersonTest() throws Exception {
        mockMvc.perform(delete("/person/delete").param("firstname", "John").param("lastName", "Boyd").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
    }

    //get une liste des personnes couvertes par la caserne de pompiers correspondante (test)
    @Test
    void getPersonByStation() throws Exception {
        mockMvc.perform(get("/firestation").param("stationNumber", "3")).andExpect(status().isOk()).andReturn();
    }

    // ********************* à finir
    @Test
    void getChildrenByAddress() throws Exception {
        mockMvc.perform(get("/childAlert").param("address", "1509 Culver St")).andExpect(status().isOk());
    }

    //get une liste des numéros de téléphone des résidents desservis par la caserne de pompiers
    @Test
    void getPhoneNumber() throws Exception {
        mockMvc.perform(get("/phoneAlert").param("firestation", "3")).andExpect(status().isOk());
    }

    //retourner la liste des habitants vivant à l’adresse donnée ainsi que le numéro de la caserne
//     de pompiers la desservant
    @Test
    void getInhabitantByAddress() throws Exception {
        mockMvc.perform(get("/fire").param("address", "892 Downing Ct")).andExpect(status().isOk());
    }
//
    @Test
    void getFoyerByStationList() throws Exception {
        mockMvc.perform(get("/flood/stations").param("stations", "3")).andExpect(status().isOk());
    }
//url doit retourner le nom, l'adresse, l'âge, l'adresse mail et les antécédents médicaux (médicaments,
//    posologie, allergies) de chaque habitant
    @Test
    void getPersonByFirstNameAndLastName() throws Exception {
        mockMvc.perform(get("/personInfo").param("firstName", "John").param("lastName","Boyd")).andExpect(status().isOk());
    }

    @Test
    void getAddressMailByCity() throws Exception {
        mockMvc.perform(get("/communityEmail").param("city", "Culver")).andExpect(status().isOk());
    }

}

