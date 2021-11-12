package com.SafetyNet.SafetyNetAlerts.service;

import com.SafetyNet.SafetyNetAlerts.model.Medicalrecord;
import com.SafetyNet.SafetyNetAlerts.repository.MedicalrecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MedicalrecordService {
    private MedicalrecordRepository medicalrecordRepository;

    @Autowired
    public MedicalrecordService(MedicalrecordRepository medicalrecordRepository) {
        this.medicalrecordRepository = medicalrecordRepository;
    }

    public Medicalrecord saveMedicalrecord(Medicalrecord medicalrecord) {
        return medicalrecordRepository.save(medicalrecord);
    }

    public Iterable<Medicalrecord> saveMedicalrecords(List<Medicalrecord> medicalrecords) {
        return medicalrecordRepository.saveAll(medicalrecords);
    }


    public void deleteMedicalrecord(Medicalrecord medicalrecord) {
        medicalrecordRepository.deleteById(medicalrecord.getId());
    }

    public Optional<Medicalrecord> getMedicalrecord(final Long id) {
        return medicalrecordRepository.findById(id);
    }

    public Iterable<Medicalrecord> getMedicalrecords() {
        return medicalrecordRepository.findAll();
    }

    public Medicalrecord updateMedicalrecord(Medicalrecord medicalrecordUpdated) {
        Medicalrecord medicalrecord = medicalrecordRepository.findById(medicalrecordUpdated.getId()).orElseThrow(RuntimeException::new);
        medicalrecordUpdated.setBirthdate(medicalrecord.getBirthdate());
        medicalrecordUpdated.setMedications(medicalrecord.getMedications());
        medicalrecordUpdated.setAllergies(medicalrecord.getAllergies());
        return medicalrecordRepository.save(medicalrecordUpdated);
    }
}
