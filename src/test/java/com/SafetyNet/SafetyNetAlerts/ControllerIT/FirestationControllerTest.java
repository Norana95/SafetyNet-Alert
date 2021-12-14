package com.SafetyNet.SafetyNetAlerts.ControllerUnitTest;

import com.SafetyNet.SafetyNetAlerts.controller.FirestationController;
import com.SafetyNet.SafetyNetAlerts.model.Firestation;
import com.SafetyNet.SafetyNetAlerts.service.FirestationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = FirestationController.class)
public class FirestationControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    FirestationService firestationService;

    @Autowired
    ObjectMapper objectMapper;

    @InjectMocks
    FirestationController firestationController;

    //ajout d'un mapping caserne/adresse ;
    @Test
    void saveFirestation() throws Exception {
        Firestation firestation = new Firestation("2 Boulevard Magenta", "1");
        String jsonFirestation = objectMapper.writeValueAsString(firestation);
        mockMvc.perform(post("/firestation/save").accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON).content(jsonFirestation)).andExpect(status().isOk());
    }
    //mettre à jour
    @Test
    void updateStationOfFirestationTest() throws Exception {
        Firestation firestation = new Firestation("644 Gershwin Cir", "1");
        String jsonPerson = objectMapper.writeValueAsString(firestation);
        mockMvc.perform(patch("/firestation/update").param("address", "2 Boulevard Magenta").param("stationNumber", "3").contentType(MediaType.APPLICATION_JSON).content(jsonPerson)).andExpect(status().isOk());
    }
    //supprimer le mapping d'une caserne ou d'une adresse test
    @Test
    void deleteFirestationTest() throws Exception {
        mockMvc.perform(delete("/firestation/delete").param("address", "1509 Culver St").param("stationNumber", "3").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
    }
}
