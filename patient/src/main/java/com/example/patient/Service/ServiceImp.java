package com.example.patient.Service;

import com.example.patient.Bean.PatientRequest;
import com.example.patient.Entity.Patient;
import com.example.patient.Exception.PatientNotFoundException;
import com.example.patient.Repo.PatientRespositry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServiceImp implements ServiceInt {

    @Autowired
    private PatientRespositry repo;

    @Override
    public String healthCheck() {
        return "OK";
    }

    @Override
    public ResponseEntity<?> createPatient(PatientRequest patientRequest) {
        try {
            Patient patient = new Patient();
            patient.setPatientName(patientRequest.getPatientName());
            patient.setAge(patientRequest.getAge());
            patient.setPatientEmail(patientRequest.getPatientEmail());
            patient.setPatientAddress((patientRequest.getPatientAddress()));
            repo.save(patient);
            return new ResponseEntity<>(patient, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.NOT_FOUND);
        }

    }

    @Override
    public Patient getPatientById(int id) {
        return repo.findById(id).orElseThrow(() -> new PatientNotFoundException());
    }

    @Override
    public ResponseEntity<?> getAllPatient() {
        List<Patient> all = repo.findAll();
        if (!all.isEmpty()) {
            return new ResponseEntity<>(all, HttpStatus.OK);
        }
        return new ResponseEntity<>("Not found", HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity<?> updatePatient(PatientRequest patientRequest, int id) {
        Optional<Patient> existingPatient = repo.findById(id);
        if(existingPatient.isPresent()){
            Patient patient = existingPatient.get();
            System.out.println(patient.getPatientID());
            if(patientRequest.getPatientName()!=null){
                patient.setPatientName(patientRequest.getPatientName());
            }
            if(patientRequest.getAge()!=0){
                patient.setAge(patientRequest.getAge());
            }
            if(patientRequest.getPatientAddress()!=null){
                patient.setPatientAddress(patientRequest.getPatientAddress());
            }
            if(patientRequest.getPatientEmail()!=null){
                patient.setPatientEmail((patientRequest.getPatientEmail()));
            }
            repo.save(patient);
            return new ResponseEntity<>(patient,HttpStatus.CREATED);
        }

        return new ResponseEntity<>("ID not found",HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity<?> deletePatientById(int id) {
        Optional<Patient> existingPatient = repo.findById(id);
        if(existingPatient.isPresent()){
            repo.deleteById(id);
            return new ResponseEntity<>("Patient with "+id+" is Deleted",HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>("No Patient with"+id+" found",HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity<?> getPatientByAddress(String address) {
        List<Patient> recordByAddress = repo.getRecordByAddress(address);

        if(recordByAddress.isEmpty()){
            return new ResponseEntity<>("No Patient Found with address as"+address, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(recordByAddress,HttpStatus.FOUND);
    }


}
