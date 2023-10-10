package com.aacademy.moonlight.service.password.impl;

import com.aacademy.moonlight.entity.user.User;
import com.aacademy.moonlight.exceptions.BadRequestException;
import com.aacademy.moonlight.repository.user.UserRepository;
import com.aacademy.moonlight.service.mail.EmailService;
import com.aacademy.moonlight.service.password.PasswordService;
import jakarta.persistence.EntityNotFoundException;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


@Service
public class PasswordServiceImpl implements PasswordService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final EmailService emailService;

    public PasswordServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, EmailService emailService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.emailService = emailService;
    }

    @Override
    public void sendStaticPasswordEmail(String userEmail) {
        userEmail = userEmail.toLowerCase();
        if (!isValidEmail(userEmail)) {
            throw new BadRequestException("Invalid email address");
        }

        User currentUser = null;
        List<User> allUsers = userRepository.findAll();
        for (User user : allUsers) {
            if (user.getEmail().equals(userEmail)) {
                currentUser = user;
            }
        }
        if (currentUser != null) {
            String randomPassword = generateRandomPassword();
            String hashedPassword = passwordEncoder.encode(randomPassword);
            currentUser.setPassword(hashedPassword);
            userRepository.save(currentUser);
            emailService.sendEmail(userEmail, "Forgotten Password", "Your static password is " + randomPassword);
        } else {
            throw new EntityNotFoundException("User with this email not found");
        }

    }

    private String generateRandomPassword() {
        String numbers = "0123456789";
        String uppercaseLetters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lowercaseLetters = "abcdefghijklmnopqrstuvwxyz";
        String specialCharacters = "!@#$%^&*()_+-=[]{};':,.<>?/";

        SecureRandom random = new SecureRandom();
        StringBuilder password = new StringBuilder();

        password.append(numbers.charAt(random.nextInt(numbers.length())));
        password.append(uppercaseLetters.charAt(random.nextInt(uppercaseLetters.length())));
        password.append(lowercaseLetters.charAt(random.nextInt(lowercaseLetters.length())));
        password.append(specialCharacters.charAt(random.nextInt(specialCharacters.length())));

        int totalLength = 10;
        int remainingLength = totalLength - 4;

        String allCharacters = numbers + uppercaseLetters + lowercaseLetters + specialCharacters;

        for (int i = 0; i < remainingLength; i++) {
            int randomIndex = random.nextInt(allCharacters.length());
            password.append(allCharacters.charAt(randomIndex));
        }

        String shuffledPassword = shuffleString(password.toString());

        return shuffledPassword;
    }

    private String shuffleString(String input) {
        List<Character> characters = new ArrayList<>();
        for (char c : input.toCharArray()) {
            characters.add(c);
        }

        Collections.shuffle(characters);
        StringBuilder result = new StringBuilder();
        for (Character character : characters) {
            result.append(character);
        }
        return result.toString();
    }

    private boolean isValidEmail(String email) {
        if (!email.matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}")) {
            return false;
        }
        return true;
    }
}
