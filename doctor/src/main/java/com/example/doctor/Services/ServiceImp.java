package com.example.doctor.Services;

import com.example.doctor.Beans.DoctorRequest;
import com.example.doctor.Entity.Doctor;
import com.example.doctor.Repositry.DoctorRepositry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceImp implements ServiceInt{

    @Autowired
    private DoctorRepositry repo;

    @Override
    public ResponseEntity<?> saveDoctor(DoctorRequest doctorRequest) {
        try {
            Doctor doctor=new Doctor();
            doctor.setDoctorName(doctorRequest.getDoctorName());
            doctor.setDoctorEmail(doctorRequest.getDoctorEmail());
            doctor.setDoctorAddress(doctorRequest.getDoctorAddress());
            doctor.setSpecialization(doctorRequest.getSpecialization());
            repo.save(doctor);
            return new ResponseEntity<>(doctor, HttpStatus.CREATED);
        } catch (Exception e) {
          return new ResponseEntity<>(e,HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<?> getAllDoctors() {
        List<Doctor> all = repo.findAll();
        if(!all.isEmpty()){
            return new ResponseEntity<>(all,HttpStatus.FOUND);
        }

        return new ResponseEntity<>("No Records Found",HttpStatus.NOT_FOUND);
    }
}
