package com.aacademy.moonlight.converter.hotel;

import com.aacademy.moonlight.dto.hotel.RoomRequest;
import com.aacademy.moonlight.entity.hotel.Room;
import com.aacademy.moonlight.repository.hotel.RoomRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class RoomConverter {

    @Autowired
    private final RoomRepository repository;

    public Room createRoom (Long roomId, RoomRequest request){

        return  Room.builder()
                .roomNumber(request.getRoomNumber())
                .price(request.getPrice())
                .type(request.getType())
                .view(request.getView())
                .area(request.getArea())
                .build();

    }
    public Room toRoomResponse(Room room){
        return new Room(room.getId(), room.getRoomNumber(), room.getPrice(), room.getArea(), room.getType(),
                room.getView(),room.getFacilities(),room.getRoomReservations());
    }

}
