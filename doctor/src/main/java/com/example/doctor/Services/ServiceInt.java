package com.example.doctor.Services;

import com.example.doctor.Beans.DoctorRequest;
import org.springframework.http.ResponseEntity;

public interface ServiceInt {
    ResponseEntity<?> saveDoctor(DoctorRequest doctorRequest);
    ResponseEntity<?> getAllDoctors();
}
