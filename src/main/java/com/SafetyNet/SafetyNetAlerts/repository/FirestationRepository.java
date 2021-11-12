package com.SafetyNet.SafetyNetAlerts.repository;

import com.SafetyNet.SafetyNetAlerts.model.Firestation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FirestationRepository extends CrudRepository<Firestation, Long> {
}
