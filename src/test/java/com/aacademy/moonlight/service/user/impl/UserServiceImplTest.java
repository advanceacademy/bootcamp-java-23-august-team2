package com.aacademy.moonlight.service.user.impl;
import com.aacademy.moonlight.converter.user.UserConverter;
import com.aacademy.moonlight.dto.user.UserRequest;
import com.aacademy.moonlight.dto.user.UserResponse;
import com.aacademy.moonlight.dto.user.UserUpdate;
import com.aacademy.moonlight.entity.user.User;
import com.aacademy.moonlight.repository.user.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Optional;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    private UserRepository repository;

    @Mock
    private UserConverter converter;

    @InjectMocks
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

        User user = new User();
        user.setId(1L);
        user.setFirstName("Georgi");
        UserRequest request = new UserRequest();


        when(converter.createUser(request)).thenReturn(user);
        when(repository.save(user)).thenReturn(user);
        when(converter.toUserResponse(user)).thenReturn(new UserResponse());

        //when

       UserResponse response = userService.createUser(request);

        //then

        assertNotNull(response);
        verify(converter, times(1)).createUser(request);
        verify(repository, times(1)).save(user);
        verify(converter,times(1)).toUserResponse(user);
    }

    @Test
    void deleteUserById() {
        Long userId = 1L;
        userService.deleteUserById(userId);
        verify(repository, times(1)).deleteById(userId);
    }

    @Test
    void findUserById() {
        //given
        Long userId = 1L;
        User user = new User();
        user.setId(userId);
        user.setFirstName("Georgi");

        UserResponse expectedResponse = new UserResponse();

        //when
        when(repository.findById(userId)).thenReturn(Optional.of(user));
        when(converter.toUserResponse(user)).thenReturn(expectedResponse);

//        assertThatThrownBy(()->userService.findUserById(2L))
//                .isInstanceOf(RuntimeException.class)
//                .hasMessage("User with this id was not found");

        //when
       UserResponse response = userService.findUserById(userId);
        //then
        assertNotNull(response);
        verify(converter, times(1)).toUserResponse(user);
        assertEquals(expectedResponse,response);
    }
    @Test
    void upDateUser() {
        //given
        UserUpdate update = UserUpdate.builder()
                .firstName("Allan")
                .lastName("Bob")
                .phone("+3591111111111")
                .build();

        UserRequest request = UserRequest.builder()
                .firstName("Ivan")
                .lastName("Georgiev")
                .phoneNumber("1234567")
                .email("alabalanica@abv.bg")
                .build();

        Long userId = 1L;
        User existingUser = User.builder()
                .firstName("Georgi")
                .lastName("Petkov")
                .phoneNumber("7654321")
                .email("newemail@gmail.com")
                .build();
        when(repository.findById(userId)).thenReturn(Optional.of(existingUser));
        when(repository.save(existingUser)).thenReturn(existingUser);

        UserResponse expectedResponse = new UserResponse();
        when(converter.toUserResponse(existingUser)).thenReturn(expectedResponse);

        //when
        UserResponse response = userService.updateUser(update);
        //then
        assertNotNull(response);
        verify(repository,times(1)).findById(userId);
        verify(repository,times(1)).save(existingUser);
        verify(converter,times(1)).toUserResponse(existingUser);
        assertEquals(expectedResponse,response);
    }



//    @Test
//    void existUserByEmail() {
//    }

//    @Test
//    void getAllUsers() {
//    }

//    @Test
//    void upDatePassword() {
//    }

//    @Test
//    void randomPassword() {
//    }
}