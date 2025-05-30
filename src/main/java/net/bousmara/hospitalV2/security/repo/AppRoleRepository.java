package net.bousmara.hospitalV2.security.repo;

import net.bousmara.hospitalV2.security.entities.AppRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppRoleRepository extends JpaRepository<AppRole, String> {
}