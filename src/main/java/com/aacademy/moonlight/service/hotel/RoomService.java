package com.aacademy.moonlight.service.hotel;

import com.aacademy.moonlight.dto.hotel.RoomRequest;
import com.aacademy.moonlight.entity.hotel.Room;

import java.util.Optional;

public interface RoomService {

    Room saveRoom(RoomRequest roomRequest);

    Optional<Room> getRoom(Long id);

    void deleteRoom(Long id);
}
