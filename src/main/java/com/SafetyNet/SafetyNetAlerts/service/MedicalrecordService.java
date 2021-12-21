package com.SafetyNet.SafetyNetAlerts.service;

import com.SafetyNet.SafetyNetAlerts.model.Medicalrecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicalrecordService {

    @Autowired
    DataService dataService;

    public MedicalrecordService(DataService dataService) {
        this.dataService = dataService;
    }

    public List<Medicalrecord> getMedicalrecordList() {
        return dataService.getMedicalrecords();
    }

    // ajouter un dossier médical
    public Medicalrecord saveMedicalrecords(Medicalrecord medicalrecords) {
        Medicalrecord newMedicalrecord = new Medicalrecord();
        newMedicalrecord.setFirstName(medicalrecords.firstName);
        newMedicalrecord.setLastName(medicalrecords.lastName);
        newMedicalrecord.setBirthdate(medicalrecords.birthdate);
        newMedicalrecord.setMedications(medicalrecords.medications);
        newMedicalrecord.setAllergies(medicalrecords.allergies);
        List<Medicalrecord> medicalrecordList = dataService.getMedicalrecords();
        medicalrecordList.add(newMedicalrecord);
        return medicalrecordList.get(medicalrecordList.indexOf(newMedicalrecord));
    }

    //mettre à jour un dossier medical existatnt
    public void updateMedicalrecord(String firstName, String lastName, Medicalrecord medicalrecord) {
        List<Medicalrecord> medicalrecordList = dataService.getMedicalrecords();
        for (Medicalrecord medicalrecord1 : medicalrecordList) {
            if (medicalrecord1.firstName.equals(firstName) && medicalrecord1.lastName.equals(lastName)) {
                medicalrecord1.setFirstName(medicalrecord.firstName);
                medicalrecord1.setLastName(medicalrecord.lastName);
                medicalrecord1.setBirthdate(medicalrecord.birthdate);
                medicalrecord1.setMedications(medicalrecord.medications);
                medicalrecord1.setAllergies(medicalrecord.allergies);
                medicalrecordList.set(medicalrecordList.indexOf(medicalrecord1), medicalrecord1);
                break;
            }
        }
    }

    //supprimer un dossier medical
    public void deleteMedicalrecord(String firstname, String lastname) {
        List<Medicalrecord> medicalrecordList = dataService.getMedicalrecords();
        medicalrecordList.removeIf(medicalrecord -> medicalrecord.getFirstName().equals(firstname) && medicalrecord.getLastName().equals(lastname));
    }
}

