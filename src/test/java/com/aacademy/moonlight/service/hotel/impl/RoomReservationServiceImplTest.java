package com.aacademy.moonlight.service.hotel.impl;
import com.aacademy.moonlight.dto.hotel.RoomReservationRequest;
import com.aacademy.moonlight.entity.hotel.Room;
import com.aacademy.moonlight.entity.hotel.RoomReservation;
import com.aacademy.moonlight.entity.user.User;
import com.aacademy.moonlight.repository.hotel.RoomRepository;
import com.aacademy.moonlight.repository.hotel.RoomReservationRepository;
import com.aacademy.moonlight.repository.user.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class RoomReservationServiceImplTest {

    @InjectMocks
    private RoomReservationServiceImpl roomReservationService;

    @Mock
    private RoomReservationRepository roomReservationRepository;

    @Mock
    private RoomRepository roomRepository;

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateRoomReservation() {
        // Create a RoomReservationRequest object for testing
        RoomReservationRequest reservationRequest = new RoomReservationRequest();
        reservationRequest.setStartDate(LocalDate.now());
        reservationRequest.setEndDate(LocalDate.now().plusDays(3));
        reservationRequest.setAdults(2);
        reservationRequest.setChildren(0);
        reservationRequest.setPaymentStatus(false);
        reservationRequest.setRoomId(1L);
        reservationRequest.setUserId(2L);

        // Create mock Room and User objects
        Room mockRoom = new Room();
        User mockUser = new User();

        // Set up mock repository responses
        when(roomRepository.findById(1L)).thenReturn(Optional.of(mockRoom));
        when(userRepository.findById(2L)).thenReturn(Optional.of(mockUser));
        when(roomReservationRepository.save(any(RoomReservation.class))).thenReturn(new RoomReservation());

        // Create the room reservation
        RoomReservation createdReservation = roomReservationService.createRoomReservation(reservationRequest);

        assertNotNull(createdReservation);
        verify(roomRepository, times(1)).findById(1L);
        verify(userRepository, times(1)).findById(2L);
        verify(roomReservationRepository, times(1)).save(any(RoomReservation.class));
    }

    @Test
    public void testFindRoomReservationById() {
        Long reservationId = 1L;
        RoomReservation mockReservation = new RoomReservation();
        mockReservation.setId(reservationId);

        when(roomReservationRepository.findById(reservationId)).thenReturn(Optional.of(mockReservation));

        Optional<RoomReservation> foundReservation = roomReservationService.findRoomReservationById(reservationId);

        assertTrue(foundReservation.isPresent());
        assertEquals(reservationId, foundReservation.get().getId());
        verify(roomReservationRepository, times(1)).findById(reservationId);
    }

    @Test
    public void testDeleteRoomReservation() {
        Long reservationId = 1L;

        roomReservationService.deleteRoomReservation(reservationId);

        verify(roomReservationRepository, times(1)).deleteById(reservationId);
    }

    @Test
    public void testUpdateRoomReservation() {
        Long reservationId = 1L;
        RoomReservationRequest reservationRequest = new RoomReservationRequest();
        reservationRequest.setAdults(3);

        RoomReservation mockReservation = new RoomReservation();
        mockReservation.setId(reservationId);

        when(roomReservationRepository.findById(reservationId)).thenReturn(Optional.of(mockReservation));
        when(roomReservationRepository.save(any(RoomReservation.class))).thenReturn(mockReservation);

        RoomReservation updatedReservation = roomReservationService.updateRoomReservation(reservationId, reservationRequest);

        assertNotNull(updatedReservation);
        assertEquals(3, updatedReservation.getAdults());
        verify(roomReservationRepository, times(1)).findById(reservationId);
        verify(roomReservationRepository, times(1)).save(any(RoomReservation.class));
    }
}