package com.aacademy.moonlight.service.user.impl;
import com.aacademy.moonlight.converter.user.UserConverter;
import com.aacademy.moonlight.dto.user.*;
import com.aacademy.moonlight.entity.user.User;
import com.aacademy.moonlight.exceptions.BadRequestException;
import com.aacademy.moonlight.repository.user.UserRepository;
import com.aacademy.moonlight.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository repository;
    private final UserConverter converter;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository repository, UserConverter converter, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.converter = converter;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserResponse createUser(UserRequest request) {
        if (existUserByEmail(request.getEmail())){
            return converter.toError("User with the same email already exists");
        }
        User user = converter.createUser(request);
        User savedUser = repository.save(user);
        return converter.toUserResponse(savedUser);
    }

    @Override
    public void deleteUserById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public UserResponse findUserById(Long id) {
        User user = repository.findById(id).orElseThrow(()-> new RuntimeException("User with this id was not found"));
        return converter.toUserResponse(user);
    }

    @Override
         public UserResponse findUserByEmail(String email) {
        User user = repository.findByEmail(email).orElseThrow(
                ()-> new RuntimeException("User with such email was not found"));
        return converter.toUserResponse(user);
    }

    @Override
    public boolean existUserByEmail(String email) {
        return repository.findAll().stream().anyMatch(user -> user.getEmail().equals(email));
    }

    @Override
    public List<UserResponse> getAllUsers() {
        List<User> userList = repository.findAll();
        List<UserResponse> allUsers = new ArrayList<>();
                for(User a : userList){
                    UserResponse response = converter.toUserResponse(a);
                    allUsers.add(response);
                }
        return allUsers;
    }

    @Override
    public UserResponse upDatePassword(UserChangePasswordRequest request) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) auth.getPrincipal();
        if (request.getPassword().equals(request.getConfirmPassword())){
            String hashedPassword = passwordEncoder.encode(request.getPassword());
            user.setPassword(hashedPassword);
            User savedUser = repository.save(user);
            return converter.toUserResponse(savedUser);
        }else {
            throw new BadRequestException("Password must match");
        }

    }

    @Override
    public UserResponse updateUser(UserUpdate request) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) auth.getPrincipal();

        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setPhoneNumber(request.getPhone());

        User savedUser = repository.save(user);
        return converter.toUserResponse(savedUser);
    }

    @Override
    public void randomPassword(String email) {

    }

    @Override
    public List<UserResponse> findUsersByFirstName(String firstName) {
        List<UserResponse> users = new ArrayList<>();
        for (User user : repository.findAll())
            if( user.getFirstName().equals(firstName)){
            users.add(converter.toUserResponse(user));
        }
        return users;
    }

    @Override
    public List<UserResponse> findUsersByLastName(String lastName) {
        List<UserResponse> users = new ArrayList<>();
        for (User user : repository.findAll())
            if( user.getFirstName().equals(lastName)){
                users.add(converter.toUserResponse(user));
            }
        return users;
    }

    @Override
    public UserResponse findUserByPhoneNumber(String number) {
        User user = repository.findByPhoneNumber(number).orElseThrow(
                ()-> new RuntimeException("User with such phone number was not found"));
        return converter.toUserResponse(user);
    }

}
