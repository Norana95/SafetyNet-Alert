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

    // ajouter un dossier médical
    public Medicalrecord saveMedicalrecords(Medicalrecord medicalrecords) {
        dataService.getMedicalrecords().add(medicalrecords);
        return dataService.getMedicalrecords().get(dataService.getMedicalrecords().indexOf(medicalrecords));
    }

    //mettre à jour un dossier medical existatnt
    public Medicalrecord updateMedicalrecord(Medicalrecord medicalrecord) {
        if (dataService.getMedicalrecords().contains(medicalrecord)) {
            medicalrecord.setBirthdate(new String());
            medicalrecord.setMedications(new String[]{});
            medicalrecord.setAllergies(new String[]{});
            System.out.println("medical updated !");
        } else {
            System.out.println("this medical record don't exists !");
        }
        return medicalrecord;
    }

    //supprimer un dossier medical
    public void deleteMedicalrecord(String firstname, String lastname) {
        List<Medicalrecord> medicalrecordList = dataService.getMedicalrecords();
        Medicalrecord medicalRecordDeleted = new Medicalrecord();
        for (Medicalrecord medicalrecord : medicalrecordList) {
            if (medicalrecord.getFirstName().equals(firstname) && medicalrecord.getLastName().equals(lastname)) {
                if (medicalrecordList.remove(medicalrecord)) {
                    medicalRecordDeleted = medicalrecord;
                    System.out.println("ce dossier medical a été supprimé ");
                }
            } else {
                System.out.println("il n'ya pas de dossier medical avec le nom et le prenom que vous avez saisi");
            }
        }
    }
}
