package com.SafetyNet.SafetyNetAlerts.controller;

import com.SafetyNet.SafetyNetAlerts.model.Firestation;
import com.SafetyNet.SafetyNetAlerts.service.FirestationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class FirestationController {
    @Autowired
    FirestationService firestationService;

    public FirestationController(FirestationService firestationService) {
        this.firestationService = firestationService;
    }

    //ajout d'un mapping caserne/adresse ;
    @PostMapping("/firestation/save")
    public Firestation saveFirestation(@RequestBody Firestation firestation) {
        return firestationService.saveFirestation(firestation);
    }

    /*@JsonView(Views.WithPhoneAndAddress.class)
    @GetMapping("/firestation")
    public CalculateNumberOfAdultAndChildren getPersonsByStationNumberOfFirestation(@RequestParam String stationNumber){
        return firestationService.getPersonsByStationNumberOfFirestation(stationNumber);
    }*/

    //mettre à jour le numéro de la caserne de pompiers d'une adresse ;
    @PatchMapping("firestation/update")
    public void updateStationOfFirestation(@RequestParam String address, @RequestParam String stationNumber) {
        firestationService.updateStationOfFirestation(address, stationNumber);
    }

    //supprimer le mapping d'une caserne ou d'une adresse.
    @DeleteMapping("firestation/delete")
    public void deleteFirestation(@RequestParam String address, @RequestParam String stationNumber) {
        firestationService.deleteFirestation(address, stationNumber);
    }


}
