package com.example.hospitalv2.demo.repository;

import com.example.hospitalv2.demo.entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Long> {
}
