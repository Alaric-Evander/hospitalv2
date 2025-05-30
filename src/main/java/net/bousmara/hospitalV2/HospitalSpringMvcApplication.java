package net.bousmara.hospitalV2;

import net.bousmara.hospitalV2.repository.PatientRepository;
import net.bousmara.hospitalV2.security.service.AccountService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.boot.CommandLineRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class HospitalSpringMvcApplication implements CommandLineRunner {

    @Autowired
    private PatientRepository patientRepository;


    public static void main(String[] args) {
        SpringApplication.run(HospitalSpringMvcApplication.class, args);

    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public void run(String... args) throws Exception {
        // 3 Methods to do it
        // 3rd one is :
            //Patient patient1= Patient.builder()
            //       .nom("Imane")
            //       .dateNaissance(new Date())
            //       .score(56)
            //      .build();

       // patientRepository.save(new Patient(null,"Mohamed", new Date(), false, 134));
        //patientRepository.save(new Patient(null,"Hanane", new Date(), false, 4321));
        //patientRepository.save(new Patient(null,"Imane", new Date(), true, 134));
    }

    @Bean
    CommandLineRunner commandLineRunnerUserDetails(AccountService accountService) {
        return args -> {
            accountService.addNewRole("USER");
            accountService.addNewRole("ADMIN");
            String pass = passwordEncoder().encode("123");
            accountService.addNewUser("user1",pass,"user1@gmail.com",pass);
            accountService.addNewUser("user2",pass,"user1@gmail.com",pass);
            accountService.addNewUser("admin",pass,"user1@gmail.com",pass);

            accountService.addRoleToUser("user1", "USER");
            accountService.addRoleToUser("user2","USER");
            accountService.addRoleToUser("admin","USER");
            accountService.addRoleToUser("admin","ADMIN");

        };
    }
}
