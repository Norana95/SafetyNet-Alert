package com.SafetyNet.SafetyNetAlerts.ControllerIT;

import com.SafetyNet.SafetyNetAlerts.controller.MedicalRecordController;
import com.SafetyNet.SafetyNetAlerts.model.Medicalrecord;
import com.SafetyNet.SafetyNetAlerts.service.MedicalrecordService;
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

@WebMvcTest(controllers = MedicalRecordController.class)
public class MedicalrecordControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    MedicalrecordService medicalrecordService;

    @Autowired
    ObjectMapper objectMapper;

    @InjectMocks
    MedicalRecordController medicalRecordController;

    // ajouter un dossier médical
    @Test
    void saveMedicalrecordTest() throws Exception {
        Medicalrecord medicalrecord = new Medicalrecord("Norhene","Boublenza","28/07/1995", new String[]{"hydrapermazol:300mg", "dodoxadin:30mg"},new String[]{"shellfish"});
        String jsonMedicalrecord = objectMapper.writeValueAsString(medicalrecord);
        mockMvc.perform(post("/medicalrecord/create").accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON).content(jsonMedicalrecord)).andExpect(status().isOk());
    }
    //mettre à jour
    @Test
    void updateMedicalrecord() throws Exception {
        Medicalrecord medicalrecord = new Medicalrecord("Jacob","Boyd","03/06/1989", new String[]{}, new String[]{});
        String jsonMedicalrecord = objectMapper.writeValueAsString(medicalrecord);
        mockMvc.perform(patch("/medicalrecord/update").param("firstName", "Jacob").param("lastName", "Boyd").contentType(MediaType.APPLICATION_JSON).content(jsonMedicalrecord)).andExpect(status().isOk());
    }
    //supprimer le mapping d'une caserne ou d'une adresse test
    @Test
    void deleteMedicalrecordTest() throws Exception {
        mockMvc.perform(delete("/medicalrecord/delete").param("firstname", "Jacob").param("lastname", "Boyd").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
    }
}
