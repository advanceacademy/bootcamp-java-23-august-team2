package com.aacademy.moonlight.service.user.impl;



import com.aacademy.moonlight.converter.user.UserRoleConverter;
import com.aacademy.moonlight.dto.user.UserRoleRequest;
import com.aacademy.moonlight.entity.user.UserRole;
import com.aacademy.moonlight.repository.user.UserRoleRepository;
import com.aacademy.moonlight.service.user.UserRoleService;
import org.springframework.stereotype.Service;

@Service

public class UserRoleServiceImpl implements UserRoleService {

    private final UserRoleRepository repository;

    private final UserRoleConverter converter;

    public UserRoleServiceImpl(UserRoleRepository repository, UserRoleConverter converter) {
        this.repository = repository;
        this.converter = converter;
    }


    @Override
    public UserRole createUserRole(UserRoleRequest roleRequest) {
            UserRole userRole = converter.toUserRole(roleRequest);
            return repository.save(userRole);
    }

    @Override
    public UserRole findUserRoleById(Long id) {
        UserRole userRole = repository.findById(id).orElseThrow(() -> new RuntimeException("User role not found"));
        return userRole;
    }

    @Override
    public UserRole updateUserRole(Long id, UserRoleRequest request) {
        UserRole userRole = repository.findById(id).orElseThrow(() -> new RuntimeException("User role not found"));
        if(request.getUserRoleName() != null)
        {
            userRole.setUserRole(request.getUserRoleName());
        }
        UserRole updatedUserRole = repository.save(userRole);
        return updatedUserRole;
    }

    @Override
    public void deleteUserRoleById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public UserRole findUserRoleByRole(String userRole) {
        return repository.findByUserRole(userRole).orElseThrow(() -> new RuntimeException("User role not found"));
    }
}





