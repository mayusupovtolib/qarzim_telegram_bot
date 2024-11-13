package uz.qarzim.qarzim_uz.service;

import uz.qarzim.qarzim_uz.entity.User;
import uz.qarzim.qarzim_uz.payload.LoginDto;
import uz.qarzim.qarzim_uz.repository.UserRepository;
import uz.qarzim.qarzim_uz.responseObject.AllApiResponse;
import uz.qarzim.qarzim_uz.secret.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final JwtProvider jwtProvider;

    @Transactional(rollbackFor = Exception.class)
    public HttpEntity<?> login(LoginDto loginDto) {
        try {
            Optional<User> optionalUser = userRepository.findByUsernameAndDeletedFalseAndEnabledTrue(loginDto.getUsername());
            if (optionalUser.isPresent()) {
                User user = optionalUser.get();
                if (passwordEncoder.matches(loginDto.getPassword(), user.getPassword())) {
                    String token = jwtProvider.generateToken(user);
                    HashMap<String, Object> result = new HashMap<>();
                    result.put("token", token);
                    return AllApiResponse.response(200, 1, "Success", result);
                }
            }
            return AllApiResponse.response(401, 0, "Username or password is incorrect!");
        } catch (Exception e) {
            return AllApiResponse.response(500, 0, "Error", e.getMessage());
        }
    }
}
