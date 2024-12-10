package com.example.doctor.Repositry;

import com.example.doctor.Entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoctorRepositry extends JpaRepository<Doctor,Integer> {
}
