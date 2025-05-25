package com.example.hospitalv2.demo;

import com.example.hospitalv2.demo.entities.Patient;
import com.example.hospitalv2.demo.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;

@SpringBootApplication
public class HospitalV2Application implements CommandLineRunner {
	@Autowired
	private PatientRepository patientRepository;

	public static void main(String[] args) {
		SpringApplication.run(HospitalV2Application.class, args);
	}
	@Override
	public void run(String... args) throws Exception {
		//patientRepository.save(new Patient(null,"Yassine", new Date(),false,34));
		//patientRepository.save(new Patient(null,"Ilham", new Date(),false,12));
		//patientRepository.save(new Patient(null,"Oumaima", new Date(),true,1));



	}

}
