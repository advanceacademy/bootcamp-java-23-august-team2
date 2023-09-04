package com.aacademy.moonlight.service.hotel.impl;

import com.aacademy.moonlight.dto.hotel.RoomRequest;
import com.aacademy.moonlight.entity.hotel.Room;
import com.aacademy.moonlight.entity.hotel.RoomFacility;
import com.aacademy.moonlight.repository.hotel.RoomFacilityRepository;
import com.aacademy.moonlight.repository.hotel.RoomRepository;
import com.aacademy.moonlight.service.hotel.RoomService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoomServiceImpl implements RoomService {
    private final RoomRepository roomRepository;
    private final RoomFacilityRepository roomFacilityRepository;

    public RoomServiceImpl(RoomRepository roomRepository, RoomFacilityRepository roomFacilityRepository) {
        this.roomRepository = roomRepository;
        this.roomFacilityRepository = roomFacilityRepository;
    }

    @Override
    public Room saveRoom( @Valid RoomRequest roomRequest) {
        // Create a new Room entity and set its properties
        Room room = new Room();
        room.setRoomNumber(roomRequest.getRoomNumber());
        room.setPrice(roomRequest.getPrice());
        room.setType(roomRequest.getType());
        room.setView(roomRequest.getView());

        List<Long> facilityIds = roomRequest.getFacilityIds();
        if (facilityIds != null && !facilityIds.isEmpty()) {
            List<RoomFacility> facilities = roomFacilityRepository.findAllById(facilityIds);
            room.setFacilities(facilities);
        }

        return roomRepository.save(room);
    }

    @Override
    public Optional<Room> getRoom(Long id) {
        return roomRepository.findById(id);
    }

    @Override
    public void deleteRoom(Long id) {
        roomRepository.deleteById(id);
    }

}


