package com.SafetyNet.SafetyNetAlerts.controller;

import com.SafetyNet.SafetyNetAlerts.model.Firestation;
import com.SafetyNet.SafetyNetAlerts.service.FirestationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class FirestationController {

    Logger logger = LoggerFactory.getLogger(FirestationController.class);

    @Autowired
    FirestationService firestationService;

    public FirestationController(FirestationService firestationService) {
        this.firestationService = firestationService;
    }

    //ajout d'un mapping caserne/adresse ;
    @PostMapping("/firestation/save")
    public void saveFirestation(@RequestBody Firestation firestation) {
        logger.info("inside class : FirestationController | method : saveFirestation");
        firestationService.saveFirestation(firestation);
    }

    //mettre à jour le numéro de la caserne de pompiers d'une adresse ;
    @PatchMapping("firestation/update")
    public void updateStationOfFirestation(@RequestParam String address, @RequestParam String stationNumber) {
        logger.info("inside class : FirestationController | method : updateStationOfFirestation with RequestParam : address : " + address + "station : " + stationNumber );
        firestationService.updateStationOfFirestation(address, stationNumber);
    }

    //supprimer le mapping d'une caserne ou d'une adresse.
    @DeleteMapping("firestation/delete")
    public void deleteFirestation(@RequestParam String address, @RequestParam String stationNumber) {
        logger.info("inside class : FirestationController | method : deleteFirestation with RequestParam : address : " + address + " / station : " + stationNumber );
        firestationService.deleteFirestation(address, stationNumber);
    }


}
