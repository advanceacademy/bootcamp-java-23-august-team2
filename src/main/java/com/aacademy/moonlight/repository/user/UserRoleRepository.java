package com.aacademy.moonlight.repository.user;

import com.aacademy.moonlight.entity.user.User;
import com.aacademy.moonlight.entity.user.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, Long> {

    Optional<UserRole> findByUserRole(String userRole);
}
