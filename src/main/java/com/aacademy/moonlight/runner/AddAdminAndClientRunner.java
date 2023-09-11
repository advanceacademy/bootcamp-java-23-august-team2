package com.aacademy.moonlight.runner;


import com.aacademy.moonlight.entity.user.UserRole;
import com.aacademy.moonlight.repository.user.UserRepository;
import com.aacademy.moonlight.repository.user.UserRoleRepository;
import com.aacademy.moonlight.service.user.UserRoleService;
import com.aacademy.moonlight.service.user.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class AddAdminAndClientRunner implements CommandLineRunner {

    private final UserRoleRepository userRoleRepository;

    private final UserRepository userRepository;

    private final UserRoleService userRoleService;

    private final UserService userService;

    public AddAdminAndClientRunner(UserRoleRepository userRoleRepository, UserRepository userRepository, UserRoleService userRoleService, UserService userService) {
        this.userRoleRepository = userRoleRepository;
        this.userRepository = userRepository;
        this.userRoleService = userRoleService;
        this.userService = userService;
    }


    @Override
    public void run(String... args) throws Exception {

        UserRole clientRole = new UserRole();
        clientRole.setUserRole("CLIENT");
        userRoleRepository.save(clientRole);
        UserRole savedUserRole;

        UserRole adminRole = new UserRole();
        adminRole.setUserRole("ADMIN");
        savedUserRole = userRoleRepository.save(adminRole);

        if (savedUserRole != null) {
            System.out.println("Role successfully created and saved in database");
        } else {
            System.out.println("There was a problem creating and saving the role.");
        }

        if (adminRole != null && adminRole.getUserRole().equals("Admin")) {
            System.out.println("The role 'Admin' was successfully created and saved in the repository.");
        } else {
            System.out.println("There was a problem creating and saving the 'Admin' role, or the role is not 'Admin'.");
        }






    }
}
