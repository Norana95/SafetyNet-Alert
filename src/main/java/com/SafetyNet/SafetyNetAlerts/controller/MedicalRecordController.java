package com.SafetyNet.SafetyNetAlerts.controller;

import com.SafetyNet.SafetyNetAlerts.model.Medicalrecord;
import com.SafetyNet.SafetyNetAlerts.service.MedicalrecordService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class MedicalRecordController {

    @Autowired
    MedicalrecordService medicalrecordService;

    Logger logger = LoggerFactory.getLogger(MedicalRecordController.class);

    @GetMapping("medicalrecord/list")
    public List<Medicalrecord> getMedicalrecordList() {
        logger.info("inside class : MedicalrecordController | method : getMedicalrecordList");
        return medicalrecordService.getMedicalrecordList();
    }

    // ajouter un dossier médical
    @PostMapping("medicalrecord/create")
    public void saveMedicalrecords(@RequestBody Medicalrecord medicalrecords) {
        logger.info("inside class : MedicalrecordController | method : saveMedicalrecords ");
        medicalrecordService.saveMedicalrecords(medicalrecords);
    }

    //mettre à jour un dossier medical
    @PatchMapping("medicalrecord/update")
    public void updateMedicalrecord(@RequestParam String firstName, String lastName, @RequestBody Medicalrecord medicalrecord) {
        logger.info("inside class : MedicalrecordController | method : updateMedicalrecord with RequestParam : firstName : " + firstName + " / lastName : " + lastName + "and requestBoby : medicalrecord");
        medicalrecordService.updateMedicalrecord(firstName, lastName,medicalrecord);
    }

    //supprimer un dossier medical
    @DeleteMapping("medicalrecord/delete")
    public void deleteMedicalrecord(@RequestParam String firstName, String lastName) {
        logger.info("inside class : MedicalrecordController | method : deleteMedicalrecord with RequestParam : firstname : " + firstName + " / lastName : " + lastName );
        medicalrecordService.deleteMedicalrecord(firstName, lastName);
    }
}
