package com.SafetyNet.SafetyNetAlerts.controller;

import com.SafetyNet.SafetyNetAlerts.model.Medicalrecord;
import com.SafetyNet.SafetyNetAlerts.service.MedicalrecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class MedicalRecordController {
    @Autowired
    MedicalrecordService medicalrecordService;

    @GetMapping("medicalrecord/list")
    public List<Medicalrecord> getMedicalrecordList() {
        return medicalrecordService.getMedicalrecordList();
    }

    // ajouter un dossier médical
    @PostMapping("medicalrecord/create")
    public Medicalrecord saveMedicalrecords(@RequestBody Medicalrecord medicalrecords) {
        return medicalrecordService.saveMedicalrecords(medicalrecords);
    }

    //mettre à jour un dossier medical
    @PatchMapping("medicalrecord/update")
    public void updateMedicalrecord(@RequestParam String firstName, String lastName, @RequestBody Medicalrecord medicalrecord) {
         medicalrecordService.updateMedicalrecord(firstName, lastName,medicalrecord);
    }

    //supprimer un dossier medical
    @DeleteMapping("medicalrecord/delete")
    public void deleteMedicalrecord(@RequestParam String firstname, String lastname) {
        medicalrecordService.deleteMedicalrecord(firstname, lastname);
    }
}
