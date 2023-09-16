package com.aacademy.moonlight.security.auth;
import com.aacademy.moonlight.dto.security.AuthResponse;
import com.aacademy.moonlight.dto.security.LoginRequest;
import com.aacademy.moonlight.dto.security.RegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationService service;

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> registerUser(@RequestBody RegisterRequest request){
        return ResponseEntity.ok(service.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> loginUser(@RequestBody LoginRequest request){
        return ResponseEntity.ok(service.login(request));
    }

}
