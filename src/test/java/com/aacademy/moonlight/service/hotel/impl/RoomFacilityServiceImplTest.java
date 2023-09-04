package com.aacademy.moonlight.service.hotel.impl;

import com.aacademy.moonlight.dto.hotel.RoomFacilityRequest;
import com.aacademy.moonlight.entity.hotel.RoomFacility;
import com.aacademy.moonlight.repository.hotel.RoomFacilityRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class RoomFacilityServiceImplTest {

    private RoomFacilityServiceImpl roomFacilityService;

    @Mock
    private RoomFacilityRepository roomFacilityRepository;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        roomFacilityService = new RoomFacilityServiceImpl(roomFacilityRepository);
    }

    @Test
    public void testSaveRoomFacility() {
        RoomFacilityRequest request = new RoomFacilityRequest();
        request.setFacility("Test Facility");

        RoomFacility savedRoomFacility = new RoomFacility();
        savedRoomFacility.setId(1L);
        savedRoomFacility.setFacility("Test Facility");

        when(roomFacilityRepository.save(any())).thenReturn(savedRoomFacility);

        RoomFacility result = roomFacilityService.saveRoomFacility(request);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Test Facility", result.getFacility());
        verify(roomFacilityRepository, times(1)).save(any());
    }

    @Test
    public void testGetRoomFacilityById() {
        Long roomId = 1L;
        RoomFacility roomFacility = new RoomFacility();
        roomFacility.setId(roomId);
        roomFacility.setFacility("Test Facility");

        when(roomFacilityRepository.findById(roomId)).thenReturn(Optional.of(roomFacility));

        Optional<RoomFacility> result = roomFacilityService.getRoomFacility(roomId);

        assertTrue(result.isPresent());
        assertEquals(roomId, result.get().getId());
        assertEquals("Test Facility", result.get().getFacility());
    }

    @Test
    public void testGetRoomFacilityByIdNotFound() {
        Long roomId = 1L;

        when(roomFacilityRepository.findById(roomId)).thenReturn(Optional.empty());

        Optional<RoomFacility> result = roomFacilityService.getRoomFacility(roomId);

        assertTrue(result.isEmpty());
    }

    @Test
    public void testDeleteRoomFacility() {
        Long roomId = 1L;

        roomFacilityService.deleteRoomFacility(roomId);

        verify(roomFacilityRepository, times(1)).deleteById(roomId);
    }
}