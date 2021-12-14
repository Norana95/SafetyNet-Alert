package com.SafetyNet.SafetyNetAlerts.controller;

import com.SafetyNet.SafetyNetAlerts.model.Medicalrecord;
import com.SafetyNet.SafetyNetAlerts.service.MedicalrecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/medical records")
public class MedicalRecordController {
    @Autowired
    MedicalrecordService medicalrecordService;

    // ajouter un dossier médical
    @PostMapping("medicalrecord/create")
    public Medicalrecord saveMedicalrecords(@RequestBody Medicalrecord medicalrecords) {
        return medicalrecordService.saveMedicalrecords(medicalrecords);
    }

    //mettre à jour un dossier medical
    @PatchMapping("medicalrecord/update")
    public Medicalrecord updateMedicalrecord(Medicalrecord medicalrecord) {
        return medicalrecordService.updateMedicalrecord(medicalrecord);
    }

    //supprimer un dossier medical
    @DeleteMapping("medicalrecord/delete")
    public void deleteMedicalrecord(String firstname, String lastname) {
        medicalrecordService.deleteMedicalrecord(firstname, lastname);
    }
}
