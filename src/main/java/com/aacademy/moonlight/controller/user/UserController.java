package com.aacademy.moonlight.controller.user;

import com.aacademy.moonlight.dto.user.UserChangePasswordRequest;
import com.aacademy.moonlight.dto.user.UserResponse;
import com.aacademy.moonlight.dto.user.UserUpdate;
import com.aacademy.moonlight.service.user.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/client/user")
public class UserController {
    @Autowired
    UserService userService;

    @PutMapping(path = "/update-user-details")
    public ResponseEntity<UserResponse> updateUser(@Valid @RequestBody UserUpdate request) {

        return ResponseEntity.status(HttpStatus.OK).body(userService.updateUser(request));
    }

    @PutMapping(path = "/change-password")
    public ResponseEntity<UserResponse> updatePassword(@Valid @RequestBody UserChangePasswordRequest request) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.upDatePassword(request));
    }
}
