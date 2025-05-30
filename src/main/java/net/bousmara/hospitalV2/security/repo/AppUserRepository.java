package net.bousmara.hospitalV2.security.repo;

import net.bousmara.hospitalV2.security.entities.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppUserRepository extends JpaRepository<AppUser, Long> {
    AppUser findByUsername(String username);
}
