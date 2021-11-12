package com.SafetyNet.SafetyNetAlerts.service;

import com.SafetyNet.SafetyNetAlerts.model.Firestation;
import com.SafetyNet.SafetyNetAlerts.repository.FirestationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FirestationService {

    FirestationRepository firestationRepository;

    @Autowired
    public FirestationService(FirestationRepository firestationRepository) {
        this.firestationRepository = firestationRepository;
    }

    public Firestation saveFirestation(Firestation firestation) {
        return firestationRepository.save(firestation);
    }

    public Iterable<Firestation> saveFirestations(List<Firestation> firestations) {
        return firestationRepository.saveAll(firestations);
    }


    public void deleteFirestation(Firestation firestation) {
        firestationRepository.deleteById(firestation.getId());
    }

    public Optional<Firestation> getFirestation(final Long id) {
        return firestationRepository.findById(id);
    }

    public Iterable<Firestation> getFirestations() {
        return firestationRepository.findAll();
    }

    public Firestation updateFirestation(Firestation firestationUpdated) {
        Firestation firestation = firestationRepository.findById(firestationUpdated.getId()).orElseThrow(RuntimeException::new);
        firestationUpdated.setAddress(firestation.getAddress());
        firestationUpdated.setStation(firestation.getStation());
        return firestationRepository.save(firestationUpdated);
    }
}
