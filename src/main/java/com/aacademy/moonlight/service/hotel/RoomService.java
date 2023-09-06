package com.aacademy.moonlight.service.hotel;

import com.aacademy.moonlight.dto.hotel.RoomRequest;
import com.aacademy.moonlight.entity.hotel.Room;
import com.aacademy.moonlight.entity.hotel.RoomType;

import java.util.Optional;

public interface RoomService {

    Room saveRoom(RoomRequest roomRequest);

    Room findByNumber(Integer roomNumber);
    Optional<Room> getRoom(Long id);

    void deleteRoom(Long id);
}
