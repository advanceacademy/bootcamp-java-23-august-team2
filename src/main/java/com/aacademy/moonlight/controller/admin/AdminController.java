package com.aacademy.moonlight.controller.admin;

import com.aacademy.moonlight.dto.car.CarResponse;
import com.aacademy.moonlight.dto.user.UserResponse;
import com.aacademy.moonlight.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/admin")
public class AdminController {

    @Autowired
    UserService userService;

    @GetMapping(path = "/{id}")
    public ResponseEntity<UserResponse> getUser(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.findUserById(id));
    }

    @GetMapping(path = "/user-by-email/{mail}")
    public ResponseEntity<UserResponse> getUserByEmail(@PathVariable String mail) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.findUserByEmail(mail));
    }

    @GetMapping(path = "/user-by-firstName/{name}")
    public ResponseEntity<List<UserResponse>> getUserByFirstName(@PathVariable String name) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.findUsersByFirstName(name));
    }

    @GetMapping(path = "/user-by-lastName/{name}")
    public ResponseEntity<List<UserResponse>> getUserByLastName(@PathVariable String name) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.findUsersByLastName(name));
    }

    @GetMapping(path = "/user-by-phoneNumber/{number}")
    public ResponseEntity<UserResponse> getUserByPhoneNumber(@PathVariable String number) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.findUserByPhoneNumber(number));
    }
}