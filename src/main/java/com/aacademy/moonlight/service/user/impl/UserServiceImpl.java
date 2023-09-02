package com.aacademy.moonlight.service.user.impl;
import com.aacademy.moonlight.converter.user.UserConverter;
import com.aacademy.moonlight.dto.user.UserRequest;
import com.aacademy.moonlight.dto.user.UserResponse;
import com.aacademy.moonlight.dto.user.UserUpDatePassword;
import com.aacademy.moonlight.entity.user.User;
import com.aacademy.moonlight.repository.user.UserRepository;
import com.aacademy.moonlight.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository repository;
    private final UserConverter converter;

    @Autowired
    public UserServiceImpl(UserRepository repository, UserConverter converter) {
        this.repository = repository;
        this.converter = converter;
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

//    @Override
//    public UserResponse findUserByEmail(String email) {
//        User user = repository.findByEmail(email).orElseThrow(()-> new RuntimeException("User not found"));
//        return converter.toUserResponse(user);
//    }

    @Override
    public boolean existUserByEmail(String email) {
        return repository.findAll().stream().anyMatch(user -> user.getEmail().equals(email));
    }

    @Override
    public List<UserResponse> getAllUsers() {
        return null;
    }

    @Override
    public UserResponse upDatePassword(Long id, UserUpDatePassword password) {
        return null;
    }

    @Override
    public UserResponse upDateUser(UserRequest request, Long id) {
        return null;
    }

    @Override
    public void randomPassword(String email) {

    }

}
