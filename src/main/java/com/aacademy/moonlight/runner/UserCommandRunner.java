package com.aacademy.moonlight.runner;

import com.aacademy.moonlight.entity.user.User;
import com.aacademy.moonlight.repository.user.UserRepository;
import com.aacademy.moonlight.repository.user.UserRoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class UserCommandRunner implements CommandLineRunner {
    private final UserRepository userRepository;

    private final UserRoleRepository userRoleRepository;

    public UserCommandRunner(UserRepository userRepository, UserRoleRepository userRoleRepository) {
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
    }

    @Override
    public void run(String... args) throws Exception {
//        User user = new User();
//        user.setFirstName("admin");
//        user.setLastName("admin");
//        user.setUserRole(userRoleRepository.findById(1L).orElseThrow());
//        user.setPhoneNumber("+359123456789");
//        user.setEmail("dani055@abv.bg");
//        user.setPassword("Admin@1234");
//        user.setCreatedDate(LocalDate.now());
//
//        User savedUser = userRepository.save(user);
    }
}
