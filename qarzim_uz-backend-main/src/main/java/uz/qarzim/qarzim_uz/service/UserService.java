package uz.qarzim.qarzim_uz.service;

import uz.qarzim.qarzim_uz.repository.RoleRepository;
import uz.qarzim.qarzim_uz.repository.UserRepository;
import uz.qarzim.qarzim_uz.utill.MapperDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final MapperDto mapperDto;

}
