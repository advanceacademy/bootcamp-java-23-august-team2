package com.aacademy.moonlight.service.user;
import com.aacademy.moonlight.dto.user.UserRequest;
import com.aacademy.moonlight.dto.user.UserResponse;
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
    UserResponse upDateUser(UserRequest request, Long id);
    void randomPassword(String email);
    List<UserResponse> findUsersByFirstName(String name);
    List<UserResponse> findUsersByLastName(String name);
    UserResponse findUserByPhoneNumber(String number);

}