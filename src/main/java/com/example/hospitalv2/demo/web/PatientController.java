package com.example.hospitalv2.demo.web;


import com.example.hospitalv2.demo.entities.Patient;
import com.example.hospitalv2.demo.repository.PatientRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@AllArgsConstructor
public class PatientController {

    private PatientRepository patientRepository;
    @GetMapping("/index")
    public String index(Model model){
        List<Patient> patientList = (List<Patient>) patientRepository.findAll();
        model.addAttribute("ListPatients", patientList);
        return "patients";
    }
}
