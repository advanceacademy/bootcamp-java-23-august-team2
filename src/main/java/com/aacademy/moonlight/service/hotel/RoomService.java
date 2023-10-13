package com.aacademy.moonlight.service.hotel;

import com.aacademy.moonlight.dto.hotel.RoomRequest;
import com.aacademy.moonlight.dto.hotel.RoomResponse;
import com.aacademy.moonlight.entity.hotel.Room;
import com.aacademy.moonlight.entity.hotel.RoomType;
import com.aacademy.moonlight.entity.hotel.RoomView;


import java.util.List;
import java.util.Optional;

public interface RoomService {

    RoomResponse saveRoom(RoomRequest roomRequest);
    List<RoomResponse> getAllRooms ();

    RoomResponse getRoomById (Long id);

    List<RoomResponse> findByRoomNumber(Long roomNumber);

    Optional<Room> getRoom(Long id);
    void deleteRoom(Long id);

    List<RoomResponse> findByRoomType (RoomType type);

    List<RoomResponse> findByRoomView (RoomView view);

    List<RoomResponse> findByRoomCapacity(Long id);

    List<RoomResponse> findByRoomPrice (Long price);
}
