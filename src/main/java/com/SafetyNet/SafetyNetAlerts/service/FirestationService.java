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
        boolean found = false;
        for (Firestation firestation : firestationList) {
            if (firestation.getAddress().equals(address)) {
                firestation.setStation(stationNumber);
                found = true;
            }
        }
        if (found) {
            System.out.println("la station a bien été mis à jour !");
        } else {
            System.out.println("la station n'a pas pu etre mis à  jour !");
        }
    }

    //supprimer le mapping d'une caserne ou d'une adresse.
    public void deleteFirestation(String address, String stationNumber) {
        List<Firestation> firestationList = dataService.getFirestations();
        boolean found = false;
        for (Firestation firestation : firestationList) {
            if (firestation.getAddress().equals(address) && firestation.getStation().equals(stationNumber) ) {
                firestationList.remove(firestation);
                found = true;
            }
        }
        if (found) {
            System.out.println("la station a bien été mis à jour !");
        } else {
            System.out.println("la station n'a pas pu etre mis à  jour !");
        }
    }
}
