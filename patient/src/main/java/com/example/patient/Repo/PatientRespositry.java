package com.example.patient.Repo;

import com.example.patient.Entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.NativeQuery;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PatientRespositry extends JpaRepository<Patient, Integer> {

    @Query(value = "select * from patient where patient_address=?",nativeQuery = true)
    List<Patient> getRecordByAddress(String address);
}
