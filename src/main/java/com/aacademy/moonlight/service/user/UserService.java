package com.aacademy.moonlight.service.user;
import com.aacademy.moonlight.dto.user.*;

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
    UserResponse upDatePassword(UserChangePasswordRequest request);
    UserResponse updateUser(UserUpdate request);
    void randomPassword(String email);
    List<UserResponse> findUsersByFirstName(String name);
    List<UserResponse> findUsersByLastName(String name);
    UserResponse findUserByPhoneNumber(String number);

}