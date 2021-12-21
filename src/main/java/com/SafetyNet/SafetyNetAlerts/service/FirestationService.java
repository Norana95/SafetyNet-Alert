package com.SafetyNet.SafetyNetAlerts.service;

import com.SafetyNet.SafetyNetAlerts.model.Firestation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FirestationService {

    @Autowired
    DataService dataService;

    public FirestationService(DataService dataService) {
        this.dataService = dataService;
    }


    //ajout d'un mapping caserne/adresse ;
    public Firestation saveFirestation(Firestation firestation) {
        dataService.getFirestations().add(firestation);
        return dataService.getFirestations().get(dataService.getFirestations().indexOf(firestation));
    }

    //mettre à jour le numéro de la caserne de pompiers d'une adresse
    public void updateStationOfFirestation(String address, String stationNumber) {
        List<Firestation> firestationList = dataService.getFirestations();
        for (Firestation firestation : firestationList) {
            if (firestation.getAddress().equals(address)) {
                firestation.setStation(stationNumber);
            }
        }
    }

    //supprimer le mapping d'une caserne ou d'une adresse.
    public void deleteFirestation(String address, String stationNumber) {
        List<Firestation> firestationList = dataService.getFirestations();
        firestationList.removeIf(firestation -> firestation.getAddress().equals(address) && firestation.getStation().equals(stationNumber));
        }
}
