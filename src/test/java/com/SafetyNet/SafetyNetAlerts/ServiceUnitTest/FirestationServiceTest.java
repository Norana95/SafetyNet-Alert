package com.SafetyNet.SafetyNetAlerts.ServiceUnitTest;

import com.SafetyNet.SafetyNetAlerts.controller.ReadAndConvertJsonFileToObject;
import com.SafetyNet.SafetyNetAlerts.model.Firestation;
import com.SafetyNet.SafetyNetAlerts.service.DataService;
import com.SafetyNet.SafetyNetAlerts.service.FirestationService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.List;

@SpringBootTest
public class FirestationServiceTest {

    @Autowired
    FirestationService firestationService;
    @Autowired
    DataService dataService;

    @Autowired
    ReadAndConvertJsonFileToObject readAndConvertJsonFileToObject;

    @BeforeEach
    public void initData() throws IOException {
        readAndConvertJsonFileToObject.initData();
    }

    //ajout d'un mapping caserne/adresse ;
    @Test
    void saveFirestationTest() {
        Firestation firestation = new Firestation("2 Boulevard Magenta", "1");
        Firestation firestation1 = firestationService.saveFirestation(firestation);
        Assertions.assertEquals(firestation1, firestation);
    }
    //mettre à jour
    @Test
    void updateStationOfFirestationTest() throws Exception {
        firestationService.updateStationOfFirestation("644 Gershwin Cir","3");
        List<Firestation> firestationList = dataService.getFirestations();
        Assertions.assertEquals(firestationList.get(3).station, "3");
        Assertions.assertEquals(firestationList.get(3).address, "644 Gershwin Cir");
    }

    //supprimer le mapping d'une caserne ou d'une adresse test
    @Test
    void deleteFirestationTest() throws Exception {
        String address = "112 Steppes Pl";
        String stationNumber = "4";
        firestationService.deleteFirestation(address,stationNumber);
        List<Firestation> firestationList = dataService.getFirestations();
        Assertions.assertEquals(firestationList.get(9).station, "1");
        Assertions.assertNotEquals(firestationList.get(10).address,"947 E. Rose Dr");
    }
}
