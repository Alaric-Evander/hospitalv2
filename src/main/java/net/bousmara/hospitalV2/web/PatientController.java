package net.bousmara.hospitalV2.web;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import net.bousmara.hospitalV2.entities.Patient;
import net.bousmara.hospitalV2.repository.PatientRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@AllArgsConstructor
public class PatientController {
    private PatientRepository patientRepository;
    @GetMapping("/user/index")
    public String index(Model model,
                        @RequestParam(name="page", defaultValue = "0") int page,
                        @RequestParam(name="size", defaultValue = "4") int size,
                        @RequestParam(name="keyword", defaultValue = "") String kw) {
        Page<Patient> pagePatients = patientRepository.findByNomContains(kw, PageRequest.of(page, size));
        model.addAttribute("pagePatients",pagePatients.getContent());
        model.addAttribute("pages",new int[pagePatients.getTotalPages()]);
        model.addAttribute("currentPage",page);
        model.addAttribute("keyword",kw);
        return "patients";
    }

    @GetMapping("/admin/delete")
    public String delete(Long id,@RequestParam(name="keyword", defaultValue = "") String keyword,@RequestParam(name="page", defaultValue = "0") int page){
        patientRepository.deleteById(id);
        return "redirect:/user/index?page="+page+"&keyword="+keyword;
    }

    @GetMapping("/")
    public String home(){
        return "redirect:/user/index";
    }

    @GetMapping("/admin/formPatients")
    public String formPatients(Model model){
        model.addAttribute("patient",new Patient());
        return "formPatients";
    }

    @PostMapping("/admin/save")
    public String save(Model model, @Valid Patient patient, BindingResult bindingResult,
                       @RequestParam(name="keyword", defaultValue = "") String keyword,@RequestParam(name="page", defaultValue = "0") int page){
        if(bindingResult.hasErrors())  return "formPatients";
        patientRepository.save(patient);
        System.out.println("Enregistré");
        return "redirect:/user/index?page="+page+"&keyword="+keyword;
    }

    @GetMapping("/admin/editPatient")
    public String editPatient(Model model, Long id, @RequestParam(name="keyword", defaultValue = "") String keyword,@RequestParam(name="page", defaultValue = "0") int page){
        Patient patient = patientRepository.findById(id).orElse(null);
        if(patient == null) throw new RuntimeException("Patient doesn't exist");
        model.addAttribute("patient", patient);
        model.addAttribute("keyword",keyword);
        model.addAttribute("page",page);
        return "editPatient";
    }
}
