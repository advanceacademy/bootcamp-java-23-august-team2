package com.aacademy.moonlight.controller.password;

import com.aacademy.moonlight.service.password.PasswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/v1/auth/password")
public class PasswordController {
    @Autowired
    PasswordService passwordService;

    @PostMapping(path = "/forgotten-password")
    public ResponseEntity<String> forgottenPassword(@RequestBody String email){
        passwordService.sendStaticPasswordEmail(email);
        return ResponseEntity.ok("Static password sent successfully");
    }
}
