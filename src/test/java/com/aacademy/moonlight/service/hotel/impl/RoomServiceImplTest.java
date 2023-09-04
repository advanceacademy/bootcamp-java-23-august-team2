package com.aacademy.moonlight.service.hotel.impl;

import com.aacademy.moonlight.dto.hotel.RoomRequest;
import com.aacademy.moonlight.entity.hotel.Room;
import com.aacademy.moonlight.entity.hotel.RoomFacility;
import com.aacademy.moonlight.entity.hotel.RoomType;
import com.aacademy.moonlight.entity.hotel.RoomView;
import com.aacademy.moonlight.repository.hotel.RoomFacilityRepository;
import com.aacademy.moonlight.repository.hotel.RoomRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class RoomServiceImplTest {

    private RoomServiceImpl roomService;

    @Mock
    private RoomRepository roomRepository;

    @Mock
    private RoomFacilityRepository roomFacilityRepository;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        roomService = new RoomServiceImpl(roomRepository, roomFacilityRepository);
    }

    @Test
    public void testSaveRoom() {
        RoomRequest roomRequest = new RoomRequest();
        roomRequest.setRoomNumber(101);
        roomRequest.setPrice(200.0);
        roomRequest.setType(RoomType.APARTMENT);
        roomRequest.setView(RoomView.POOL);

        // Create two facility IDs
        List<Long> facilityIds = new ArrayList<>();
        facilityIds.add(1L);
        facilityIds.add(2L);
        roomRequest.setFacilityIds(facilityIds);

        RoomFacility facility1 = new RoomFacility();
        facility1.setId(1L);

        RoomFacility facility2 = new RoomFacility();
        facility2.setId(2L);

        when(roomFacilityRepository.findAllById(facilityIds)).thenReturn(List.of(facility1, facility2));

        Room room = new Room();
        room.setId(1L);
        room.setRoomNumber(101);
        room.setPrice(200.0);
        room.setType(RoomType.APARTMENT);
        room.setView(RoomView.POOL);

        // Initialize the facilities list with the two facilities
        room.setFacilities(List.of(facility1, facility2));

        when(roomRepository.save(any(Room.class))).thenReturn(room);

        Room result = roomService.saveRoom(roomRequest);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals(101, result.getRoomNumber());
        assertEquals(200.0, result.getPrice());
        assertEquals(RoomType.APARTMENT, result.getType());
        assertEquals(RoomView.POOL, result.getView());
        assertEquals(2, result.getFacilities().size()); // Expect 2 facilities
        verify(roomFacilityRepository, times(1)).findAllById(facilityIds);
        verify(roomRepository, times(1)).save(any(Room.class));
    }

    @Test
    public void testGetRoom() {
        Long roomId = 1L;
        Room room = new Room();
        room.setId(roomId);
        room.setRoomNumber(101);
        room.setPrice(200.0);
        room.setType(RoomType.APARTMENT);
        room.setView(RoomView.POOL);

        when(roomRepository.findById(roomId)).thenReturn(Optional.of(room));

        Optional<Room> result = roomService.getRoom(roomId);

        assertTrue(result.isPresent());
        assertEquals(roomId, result.get().getId());
        assertEquals(101, result.get().getRoomNumber());
        assertEquals(200.0, result.get().getPrice());
        assertEquals(RoomType.APARTMENT, result.get().getType());
        assertEquals(RoomView.POOL, result.get().getView());
    }

    @Test
    public void testGetRoomNotFound() {
        Long roomId = 1L;

        when(roomRepository.findById(roomId)).thenReturn(Optional.empty());

        Optional<Room> result = roomService.getRoom(roomId);

        assertTrue(result.isEmpty());
    }

    @Test
    public void testDeleteRoom() {
        Long roomId = 1L;

        roomService.deleteRoom(roomId);

        verify(roomRepository, times(1)).deleteById(roomId);
    }
}