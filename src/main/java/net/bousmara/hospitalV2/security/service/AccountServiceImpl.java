package net.bousmara.hospitalV2.security.service;

import lombok.AllArgsConstructor;
import net.bousmara.hospitalV2.security.entities.AppRole;
import net.bousmara.hospitalV2.security.entities.AppUser;
import net.bousmara.hospitalV2.security.repo.AppRoleRepository;
import net.bousmara.hospitalV2.security.repo.AppUserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Transactional
@AllArgsConstructor
public class AccountServiceImpl implements AccountService {
    private final AppUserRepository appUserRepository;
    private final AppRoleRepository appRoleRepository;
    private AppRoleRepository roleRepository;
    private AppUserRepository userRepository;

    @Override
    public AppUser addNewUser(String username, String password, String email, String confirmPassword) {
        if (userRepository.findByUsername(username) == null) {
            AppUser appUser = appUserRepository.findByUsername(username);
            if (appUser != null) throw new RuntimeException("User already exists");
            if (!password.equals(confirmPassword)) throw new RuntimeException("Passwords do not match");
            appUser = AppUser.builder()
                    .userId(UUID.randomUUID().toString())
                    .username(username)
                    .password(password)
                    .email(email)
                    .build();
            AppUser savedAppUser = appUserRepository.save(appUser);
            return savedAppUser;
        }
        return null;
    }

    @Override
    public AppRole addNewRole(String role) {
            AppRole appRole = appRoleRepository.findById(role).orElse(null);
            if (appRole != null) throw new RuntimeException("Role already exists");
            appRole = roleRepository.save(AppRole.builder().role(role).build());
            return appRole;

    }

    @Override
    public void addRoleToUser(String username, String role) {
        AppUser appUser = userRepository.findByUsername(username);
        AppRole appRole = roleRepository.findById(role).get();
        appUser.getRoles().add(appRole);
        //userRepository.save(appUser);

    }

    @Override
    public void removeRoleFromUser(String username, String role) {
        AppUser appUser = appUserRepository.findByUsername(username);
        AppRole appRole = appRoleRepository.findById(role).orElse(null);
        appUser.getRoles().remove(appRole);
    }

    @Override
    public AppUser loadUserByUsername(String username) {
        return appUserRepository.findByUsername(username);
    }
}
