package com.aacademy.moonlight.service.user;
import com.aacademy.moonlight.dto.user.UserRequest;
import com.aacademy.moonlight.dto.user.UserResponse;


public interface UserService {
    UserResponse registerUser(UserRequest request);
    UserResponse createUser(UserRequest request);
    void deleteUserById (Long id);
    UserResponse findUserById(Long id);
    UserResponse findUserByEmail(String email);
    boolean existUserByEmail(String email);
}
