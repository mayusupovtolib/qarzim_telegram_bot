package uz.qarzim.qarzim_uz.controller;

import uz.qarzim.qarzim_uz.payload.LoginDto;
import uz.qarzim.qarzim_uz.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public HttpEntity<?> login(@RequestBody @Valid LoginDto loginDto){
        return authService.login(loginDto);
    }
}
