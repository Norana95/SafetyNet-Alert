package com.SafetyNet.SafetyNetAlerts.ServiceUnitTest;

import com.SafetyNet.SafetyNetAlerts.controller.ReadAndConvertJsonFileToObject;
import com.SafetyNet.SafetyNetAlerts.model.Medicalrecord;
import com.SafetyNet.SafetyNetAlerts.service.DataService;
import com.SafetyNet.SafetyNetAlerts.service.MedicalrecordService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.List;

@SpringBootTest
public class MedicalrecordServiceTest {

    @Autowired
    MedicalrecordService medicalrecordService;
    @Autowired
    DataService dataService;

    @Autowired
    ReadAndConvertJsonFileToObject readAndConvertJsonFileToObject;

    @BeforeEach
    public void initData() throws IOException {
        readAndConvertJsonFileToObject.initData();
    }

    @Test
    void saveMedicalrecordTest() {
        Medicalrecord medicalrecord = new Medicalrecord("Norhene", "Boublenza", "28/07/1995", new String[]{"hydrapermazol:300mg", "dodoxadin:30mg"}, new String[]{"shellfish"});
        Medicalrecord medicalrecordSaved = medicalrecordService.saveMedicalrecords(medicalrecord);
        Assertions.assertEquals(medicalrecordSaved.firstName, medicalrecord.firstName);
        Assertions.assertEquals(medicalrecordSaved.lastName, medicalrecord.lastName);
        Assertions.assertNotNull(medicalrecordSaved);
    }

    // mettre à jour une personne test
    @Test
    void MedicalrecordUpdateTest() {
        String firstName = "John";
        String lastName = "Boyd";
        Medicalrecord medicalrecord = new Medicalrecord("Norhene", "Boublenza", "03/06/1989", new String[]{}, new String[]{});
        medicalrecordService.updateMedicalrecord(firstName, lastName, medicalrecord);
        List<Medicalrecord> medicalrecordList = dataService.getMedicalrecords();
        Assertions.assertEquals(medicalrecordList.get(0).firstName, "Norhene");
        Assertions.assertEquals(medicalrecordList.get(0).lastName, "Boublenza");
    }

    //supprimer une personne test
    @Test
    void deleteMedicalrecordTest() {
        String firstName = "Jonanathan";
        String lastName = "Marrack";
        medicalrecordService.deleteMedicalrecord(firstName, lastName);
        List<Medicalrecord> medicalrecordList = dataService.getMedicalrecords();
        Assertions.assertNotEquals(medicalrecordList.get(6).firstName,"Jonanathan");
        Assertions.assertNotEquals(medicalrecordList.get(6).lastName,"Marrack");
        Assertions.assertEquals(medicalrecordList.get(6).firstName,"Peter");
    }
}
