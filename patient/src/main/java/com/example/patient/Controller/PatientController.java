package com.example.patient.Controller;

import com.example.patient.Bean.PatientRequest;
import com.example.patient.Entity.Patient;
import com.example.patient.Service.ServiceInt;
import jakarta.persistence.Entity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/patient")
public class PatientController {

    @Autowired
    private ServiceInt service;

    @GetMapping("/health-check")
    public String healthCheck() {
        return service.healthCheck();
    }

    @PostMapping("/savePatient")
    public ResponseEntity<?> createData(@RequestBody PatientRequest patientRequest) {
        return service.createPatient(patientRequest);
    }

    @GetMapping("/getPatient/{id}")
    public Patient getPatientbyId(@PathVariable int id) {
        return service.getPatientById(id);
    }

    @GetMapping("/getAllPatients")
    public ResponseEntity<?> getAllPatients() {
        return service.getAllPatient();
    }

    @PutMapping("/updatePatient/{id}")
    public ResponseEntity<?> updatePatients(@RequestBody PatientRequest patientRequest,@PathVariable Integer id){
        return service.updatePatient(patientRequest,id);
    }

    @DeleteMapping("/deletePatient/{id}")
    public ResponseEntity<?> deletePatient(@PathVariable int id){
        return service.deletePatientById(id);
    }

    @GetMapping("/getPatientByAddress/{address}")
    public ResponseEntity<?> getPatientByAddress(@PathVariable String address){
        return service.getPatientByAddress(address);
    }



}
