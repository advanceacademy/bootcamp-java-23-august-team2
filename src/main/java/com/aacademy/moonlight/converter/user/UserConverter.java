package com.aacademy.moonlight.converter.user;

import com.aacademy.moonlight.dto.user.UserRequest;
import com.aacademy.moonlight.dto.user.UserResponse;
import com.aacademy.moonlight.entity.user.User;
import org.springframework.stereotype.Component;

@Component
public class UserConverter {

    public User createUser (UserRequest request){
        return User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .phoneNumber(request.getPhoneNumber())
                .password(request.getPassword())
                .build();
    }

    public UserResponse toUserResponse (User user){
        return new UserResponse(user.getId(), user.getFirstName(), user.getLastName(), user.getEmail());
    }
    public UserResponse toError(String error) {
        return new UserResponse(error);
    }
}
