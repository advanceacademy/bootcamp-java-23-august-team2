package com.aacademy.moonlight.runner;

import com.aacademy.moonlight.entity.user.User;
import com.aacademy.moonlight.entity.user.UserRole;
import com.aacademy.moonlight.repository.user.UserRepository;
import com.aacademy.moonlight.repository.user.UserRoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class AddAdminAndClientRunner implements CommandLineRunner {

    private final UserRoleRepository userRoleRepository;

    private final UserRepository userRepository;

    public AddAdminAndClientRunner(UserRoleRepository userRoleRepository, UserRepository userRepository) {
        this.userRoleRepository = userRoleRepository;
        this.userRepository = userRepository;
    }




    @Override
    public void run(String... args) throws Exception {


        UserRole role = new UserRole();
        role.setUserRole("ADMIN");
        UserRole savedUserRole = userRoleRepository.save(role);

        User user = new User();
        user.setFirstName("Donald");
        user.setLastName("Trump");
        user.setEmail("donaldjtrump@gmail.com");
        user.setPassword("14061946");








    }
}
