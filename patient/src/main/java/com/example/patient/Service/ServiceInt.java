package com.example.patient.Service;

import com.example.patient.Bean.PatientRequest;
import com.example.patient.Entity.Patient;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;

public interface ServiceInt {
    String healthCheck();

    ResponseEntity<?> createPatient(PatientRequest patientRequest);

    Patient getPatientById(int id);

    ResponseEntity<?> getAllPatient();

    ResponseEntity<?> updatePatient(PatientRequest patientRequest, int id);
    ResponseEntity<?> deletePatientById(int id);
    ResponseEntity<?> getPatientByAddress(String address);
}
