package com.SafetyNet.SafetyNetAlerts.service;

import com.SafetyNet.SafetyNetAlerts.model.Firestation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FirestationService {

    @Autowired
    DataService dataService;

    Logger logger = LoggerFactory.getLogger(FirestationService.class);

    public FirestationService(DataService dataService) {
        this.dataService = dataService;
    }


    //ajout d'un mapping caserne/adresse ;
    public void saveFirestation(Firestation firestation) {
        logger.info("inside class : FirestationService | method : saveFirestation ");
        Firestation newFirestation = new Firestation();
        newFirestation.setAddress(firestation.address);
        newFirestation.setStation(firestation.station);
        try{
            dataService.getFirestations().add(newFirestation);
            logger.info("firestation saved !");
        }
        catch (Exception e){
            logger.error("error ! person not saved" + e.getMessage());
        }
    }

    //mettre à jour le numéro de la caserne de pompiers d'une adresse
    public void updateStationOfFirestation(String address, String stationNumber) {
        logger.info("inside class : FirestationService | method : updateStationOfFirestation" + "with address" + address + "and station : " + stationNumber);
        List<Firestation> firestationList = dataService.getFirestations();
        boolean found = false;
        try {
            for (Firestation firestation : firestationList) {
                if (firestation.getAddress().equals(address)) {
                    found = true;
                    firestation.setStation(stationNumber);
                    logger.info("firestation updated !");
                }
            }
        }
        catch (Exception e) {
            logger.error("error !" + e.getMessage());
        }
        if(found == false){
            logger.info("address not exist ! firestation hasn't been updated");
        }
    }

    //supprimer le mapping d'une caserne ou d'une adresse.
    public void deleteFirestation(String address, String stationNumber) {
        logger.info("inside class : FirestationService, method : deleteFirestation" + "with address : " + address + " / station : " + stationNumber);
        List<Firestation> firestationList = dataService.getFirestations();
        try{
            boolean found = firestationList.removeIf(firestation -> firestation.getAddress().equals(address) && firestation.getStation().equals(stationNumber));
            if(found) {
                logger.info("firestation deleted !");
            }
            else {
                logger.info("firestation not exists !");
            }
        }
        catch (Exception e){
            logger.error("error happened !" + e.getMessage());
        }
    }
}
