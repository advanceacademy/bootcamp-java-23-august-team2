package com.aacademy.moonlight.runner;


import com.aacademy.moonlight.dto.user.UserRequest;
import com.aacademy.moonlight.dto.user.UserRoleRequest;
import com.aacademy.moonlight.repository.user.UserRepository;
import com.aacademy.moonlight.repository.user.UserRoleRepository;
import com.aacademy.moonlight.service.user.UserRoleService;
import com.aacademy.moonlight.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class AddAdminAndClientRunner implements CommandLineRunner {

    private final UserRoleRepository userRoleRepository;

    private final UserRepository userRepository;

    private final UserRoleService userRoleService;

    private final UserService userService;

    @Autowired
    private PasswordEncoder bCryptPasswordEncoder;


    public AddAdminAndClientRunner(UserRoleRepository userRoleRepository, UserRepository userRepository, UserRoleService userRoleService, UserService userService) {
        this.userRoleRepository = userRoleRepository;
        this.userRepository = userRepository;
        this.userRoleService = userRoleService;
        this.userService = userService;
    }


    @Override
    public void run(String... args) throws Exception {

        createUserRole("admin");
        createUserRole("client");

        createUser("vetko.manqka@gmail.com");

    }

    private void createUserRole(String userRole) {
        UserRoleRequest userRoleRequest = new UserRoleRequest();
        userRoleRequest.setUserRoleName(userRole.toUpperCase());

        if (userRoleRepository.findByUserRole(userRoleRequest.getUserRoleName()).isEmpty()) {
            userRoleService.createUserRole(userRoleRequest);
        }
    }

    private void createUser (String email) {
        UserRequest userRequest =  UserRequest.builder()
                .email(email)
                .firstName("Vetko")
                .lastName("Arabadjiev")
                .phoneNumber("+359892222222")
                .password(bCryptPasswordEncoder.encode("Adm!n54321"))
                .userRole(userRoleRepository.findByUserRole("admin").orElseThrow())
                .build();

        if (userRepository.findByEmail(email).isEmpty()) {
            userService.createUser(userRequest);
        }
    }
}