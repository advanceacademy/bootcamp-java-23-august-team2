package com.aacademy.moonlight.service.user.impl;

import com.aacademy.moonlight.converter.user.UserConverter;
import com.aacademy.moonlight.dto.user.UserRequest;
import com.aacademy.moonlight.dto.user.UserResponse;
import com.aacademy.moonlight.entity.user.User;
import com.aacademy.moonlight.repository.user.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    private UserRepository repository;

    @Mock
    private UserConverter converter;

    @Mock
    private UserServiceImpl userService;

    private AutoCloseable autoCloseable;

    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        userService = new UserServiceImpl(repository, converter);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void createUser() {
        //given
        User user = new User(1L, "Georgi");
        UserRequest request = new UserRequest();
        when(converter.createUser(any())).thenReturn(user);
        when(repository.save(user)).thenReturn(user);
        when(converter.toUserResponse(user)).thenReturn(new UserResponse());

        //when

        userService.createUser(request);
        verify(converter, times(1)).createUser(any());
        verify(repository, times(1)).save(user);
        verify(converter,times(1)).toUserResponse(user);

        //then

    }

    @Test
    void deleteUserById() {
    }

    @Test
    void findUserById() {

        when(repository.findById(anyLong())).thenReturn(Optional.ofNullable(null));

        assertThatThrownBy(()->userService.findUserById(anyLong()))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("User with this id was not found");

        verify(converter, times(0)).toUserResponse(any());
    }



    @Test
    void existUserByEmail() {
    }

    @Test
    void getAllUsers() {
    }

    @Test
    void upDatePassword() {
    }

    @Test
    void upDateUser() {
    }

    @Test
    void randomPassword() {
    }
}