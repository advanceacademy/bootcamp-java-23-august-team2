package com.aacademy.moonlight.converter.hotel;

import com.aacademy.moonlight.repository.user.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class RoomConverter {

    private final UserRepository userRepository;
}
