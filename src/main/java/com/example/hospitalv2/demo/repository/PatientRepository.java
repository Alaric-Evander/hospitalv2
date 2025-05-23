package com.example.hospitalv2.demo.repository;

import com.example.hospitalv2.demo.entities.Patient;
import org.springframework.data.repository.CrudRepository;

public interface PatientRepository extends CrudRepository<Patient, Long> {
}
