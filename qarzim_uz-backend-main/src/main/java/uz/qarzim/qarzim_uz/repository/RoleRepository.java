package uz.qarzim.qarzim_uz.repository;

import uz.qarzim.qarzim_uz.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Role getRoleByName(String name);

    Optional<Role> findByNameAndDeletedFalse(String name);
}
