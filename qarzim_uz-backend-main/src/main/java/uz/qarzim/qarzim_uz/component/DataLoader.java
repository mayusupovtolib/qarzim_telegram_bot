package uz.qarzim.qarzim_uz.component;

import uz.qarzim.qarzim_uz.entity.Role;
import uz.qarzim.qarzim_uz.entity.User;
import uz.qarzim.qarzim_uz.entity.enums.RoleName;
import uz.qarzim.qarzim_uz.entity.enums.TypeOfUser;
import uz.qarzim.qarzim_uz.repository.RoleRepository;
import uz.qarzim.qarzim_uz.repository.UserRepository;
import uz.qarzim.qarzim_uz.secret.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;


@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

    private final PasswordEncoder passwordEncoder;

    private final UserRepository userRepo;

    private final RoleRepository roleRepo;

    private final JwtProvider provider;

    @Value("${spring.sql.init.mode}")
    private String mode;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void run(String... args) throws Exception {

        if (mode.equals("always")) {
            Role superAdminRole = roleRepo.save(new Role(RoleName.SUPER_ADMIN.name()));

            Set<Role> roles = new HashSet<>();
            roles.add(superAdminRole);
            User superAdmin = new User("admin", passwordEncoder.encode("123"), roles, TypeOfUser.SUPER_ADMIN.name());
            userRepo.save(superAdmin);
        }
    }
}
