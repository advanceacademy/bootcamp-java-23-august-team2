package com.aacademy.moonlight.service.user.impl;



import com.aacademy.moonlight.converter.UserRoleConverter;
import com.aacademy.moonlight.dto.user.UserRequest;
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
}





