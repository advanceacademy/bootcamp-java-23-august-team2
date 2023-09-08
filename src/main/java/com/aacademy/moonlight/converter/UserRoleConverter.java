package com.aacademy.moonlight.converter;

import com.aacademy.moonlight.dto.user.UserRoleRequest;
import com.aacademy.moonlight.entity.user.UserRole;
import org.springframework.stereotype.Component;

@Component

public class UserRoleConverter {

    public UserRole toUserRole(UserRoleRequest request){
        return UserRole.builder()
                .userRole(request.getUserRoleName())
                .build();
    }

}


