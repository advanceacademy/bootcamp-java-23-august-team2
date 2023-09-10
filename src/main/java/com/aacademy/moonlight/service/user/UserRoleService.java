package com.aacademy.moonlight.service.user;

import com.aacademy.moonlight.dto.user.UserRoleRequest;
import com.aacademy.moonlight.entity.user.UserRole;
import org.springframework.stereotype.Service;

@Service

public interface UserRoleService {
    UserRole createUserRole(UserRoleRequest roleRequest);
    UserRole findUserRoleById(Long id);
    UserRole updateUserRole(Long id, UserRoleRequest request);
    void deleteUserRoleById(Long id);

}
