package com.SafetyNet.SafetyNetAlerts.repository;

import com.SafetyNet.SafetyNetAlerts.model.Medicalrecord;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicalrecordRepository extends CrudRepository<Medicalrecord, Long> {
}
