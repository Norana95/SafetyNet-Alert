package com.SafetyNet.SafetyNetAlerts.service;

import com.SafetyNet.SafetyNetAlerts.dao.DataService;
import com.SafetyNet.SafetyNetAlerts.model.Medicalrecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicalrecordService {

    @Autowired
    DataService dataService;

    Logger logger = LoggerFactory.getLogger(FirestationService.class);

    public MedicalrecordService(DataService dataService) {
        this.dataService = dataService;
    }

    public List<Medicalrecord> getMedicalrecordList() {
        logger.info("inside class : MedicalrecordService | method : getMedicalrecordList");
        return dataService.getMedicalrecords();
    }

    // ajouter un dossier médical
    public void saveMedicalrecords(Medicalrecord medicalrecords) {
        logger.info("inside class : MedicalrecordService | method : saveMedicalrecords");
        Medicalrecord newMedicalrecord = new Medicalrecord();
        newMedicalrecord.setFirstName(medicalrecords.firstName);
        newMedicalrecord.setLastName(medicalrecords.lastName);
        newMedicalrecord.setBirthdate(medicalrecords.birthdate);
        newMedicalrecord.setMedications(medicalrecords.medications);
        newMedicalrecord.setAllergies(medicalrecords.allergies);
        List<Medicalrecord> medicalrecordList = dataService.getMedicalrecords();
        try {
            medicalrecordList.add(newMedicalrecord);
            logger.info("medical record saved !");
        } catch (Exception exception) {
            logger.info("error happened! " + exception.getMessage());
        }
    }

    //mettre à jour un dossier medical existatnt
    public void updateMedicalrecord(String firstName, String lastName, Medicalrecord medicalrecord) {
        logger.info("inside class : MedicalrecordService | method : updateMedicalrecord");
        List<Medicalrecord> medicalrecordList = dataService.getMedicalrecords();
        boolean found = false;
        try {
            for (Medicalrecord medicalrecord1 : medicalrecordList) {
                if (medicalrecord1.firstName.equals(firstName) && medicalrecord1.lastName.equals(lastName)) {
                    found = true;
                    medicalrecord1.setFirstName(medicalrecord.firstName);
                    medicalrecord1.setLastName(medicalrecord.lastName);
                    medicalrecord1.setBirthdate(medicalrecord.birthdate);
                    medicalrecord1.setMedications(medicalrecord.medications);
                    medicalrecord1.setAllergies(medicalrecord.allergies);
                    medicalrecordList.set(medicalrecordList.indexOf(medicalrecord1), medicalrecord1);
                    logger.info("the medical record has been updated !");
                    break;
                }
            }
        } catch (Exception e) {
            logger.error("error happened ! " + e.getMessage());
        }
        if (found == false) {
            logger.info("medical record don't exist ! it has not been updated.");
        }
    }

    //supprimer un dossier medical
    public void deleteMedicalrecord(String firstname, String lastname) {
        List<Medicalrecord> medicalrecordList = dataService.getMedicalrecords();
        logger.info("inside class : MedicalrecordService, method : deleteMedicalrecord" + "with firstname : " + firstname + " / lastname : " + lastname);
        try {
            boolean found = medicalrecordList.removeIf(medicalrecord -> medicalrecord.getFirstName().equals(firstname) && medicalrecord.getLastName().equals(lastname));
            if(found){
                logger.info("medical record deleted !");
            }
            else{
                logger.info("medical record not exists");
            }
        } catch (Exception e) {
            logger.error("error happened ! " + e.getMessage());
        }
    }
}

