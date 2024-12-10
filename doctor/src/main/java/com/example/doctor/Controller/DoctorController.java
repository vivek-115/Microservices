package com.example.doctor.Controller;

import com.example.doctor.Beans.DoctorRequest;
import com.example.doctor.Services.ServiceInt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/doctor")
public class DoctorController {

    @Autowired
    private ServiceInt service;

    @Autowired
    RestTemplate restTemplate;

    @PostMapping("/saveDoctor")
    public ResponseEntity<?> saveDoctor(@RequestBody DoctorRequest doctorRequest){
        return service.saveDoctor(doctorRequest);
    }

    @GetMapping("getAllDoctors")
    public ResponseEntity<?> getAllDoctors(){
        return service.getAllDoctors();
    }

    @GetMapping(value = "getAllPatients", produces = "application/json")
    public ResponseEntity<?> getAllPatients(){
        ResponseEntity<String> forEntity = restTemplate.getForEntity("http://localhost:8080/patient/getAllPatients", String.class);
        if (forEntity.getStatusCode().is2xxSuccessful()){
            return new ResponseEntity<>(forEntity.getBody(), HttpStatus.FOUND);
        }
        return new ResponseEntity<>("Not Found",HttpStatus.NOT_FOUND);
    }
}
