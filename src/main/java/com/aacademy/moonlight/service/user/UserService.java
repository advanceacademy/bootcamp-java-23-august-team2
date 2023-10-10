package com.aacademy.moonlight.service.user;
import com.aacademy.moonlight.dto.user.UserRequest;
import com.aacademy.moonlight.dto.user.UserResponse;
import com.aacademy.moonlight.dto.user.UserUpdate;
import com.aacademy.moonlight.dto.user.UserUpdatePassword;

import java.util.List;


public interface UserService {
  // UserResponse registerUser(UserRequest request);
  // security included
    UserResponse createUser(UserRequest request);
    void deleteUserById (Long id);
    UserResponse findUserById(Long id);
    UserResponse findUserByEmail(String email);
 // security included
    boolean existUserByEmail(String email);
    List<UserResponse> getAllUsers();
    UserResponse upDatePassword(Long id, UserUpdatePassword password);
    UserResponse updateUser(UserUpdate request);
    void randomPassword(String email);
}
