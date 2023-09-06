package com.aacademy.moonlight.service.user;
import com.aacademy.moonlight.dto.user.UserRequest;
import com.aacademy.moonlight.dto.user.UserResponse;
import com.aacademy.moonlight.dto.user.UserUpDatePassword;

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
    UserResponse upDatePassword(Long id, UserUpDatePassword password);
    UserResponse upDateUser(UserRequest request, Long id);
    void randomPassword(String email);
}
