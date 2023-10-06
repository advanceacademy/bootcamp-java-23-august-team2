package com.aacademy.moonlight.service.hotel.impl;
import com.aacademy.moonlight.converter.hotel.RoomConverter;
import com.aacademy.moonlight.dto.hotel.RoomResponse;
import com.aacademy.moonlight.entity.hotel.Room;
import com.aacademy.moonlight.entity.hotel.RoomType;
import com.aacademy.moonlight.entity.hotel.RoomView;
import com.aacademy.moonlight.repository.hotel.RoomFacilityRepository;
import com.aacademy.moonlight.repository.hotel.RoomRepository;
import com.aacademy.moonlight.service.hotel.RoomService;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
@AllArgsConstructor
public class RoomServiceImpl implements RoomService {

    private final RoomRepository roomRepository;
    private final RoomFacilityRepository roomFacilityRepository;
    private final RoomConverter roomConverter;


/*
    @Override
        public RoomResponse saveRoom (RoomRequest request){
            // Create a new Room entity and set its properties
            Room room = new Room();
            room.setArea(roomRequest.getArea());
            room.setRoomNumber(roomRequest.getRoomNumber());
            room.setPrice(roomRequest.getPrice());
            room.setType(roomRequest.getType());
            room.setView(roomRequest.getView());

            List<Long> facilityIds = roomRequest.getFacilityIds();
            if (facilityIds != null && !facilityIds.isEmpty()) {
                List<RoomFacility> facilities = roomFacilityRepository.findAllById(facilityIds);
                room.setFacilities(facilities);
            }
            this.roomRepository.save(room);
            Room saveRoom = roomConverter.toRoomResponse(room);
            return null;
        }
*/
    @Override
    public List<RoomResponse> getAllRooms() {

        List<Room> rooms = roomRepository.findAll();
        List<RoomResponse> responses = new ArrayList<>();
        for(Room room : rooms){
            RoomResponse response = roomConverter.toRoomResponse(room);
            responses.add(response);
        }
        return responses;
    }



    @Override
    public RoomResponse getRoomById(Integer id) {
        Room room = roomRepository.findById(id).orElseThrow(()-> new RuntimeException("Room not found!"));
        return roomConverter.toRoomResponse(room);
    }

    @Override
    public List<RoomResponse> findByRoomNumber(Integer roomNumber) {
            List<Room> rooms = roomRepository.findAll();
            return rooms.stream()
                    .filter(room -> room.getRoomNumber().equals(roomNumber))
                    .map(roomConverter::toRoomResponse)
                    .toList();
    }

    @Override
    public List<RoomResponse> findByRoomType(RoomType type) {

        List<Room> rooms = roomRepository.findAll();

        return rooms.stream()
                .filter(room -> room.getType().equals(type))
                .map(roomConverter::toRoomResponse)
                .toList();
    }

    @Override
    public List<RoomResponse> findByRoomView(RoomView view) {
        List<Room> rooms = roomRepository.findAll();

        return rooms.stream()
                .filter(room -> room.getView().equals(view))
                .map(roomConverter::toRoomResponse)
                .toList();
    }

//    @Override
//    public List<RoomResponse> findByRoomCapacity(Long id) {
//        return null;
//    }

    @Override
    public List<RoomResponse> findByRoomPrice(Long price) {
        List<Room> rooms = roomRepository.findAll();
        return rooms.stream()
                .filter(room -> room.getPrice().equals(price))
                .map(roomConverter::toRoomResponse)
                .toList();
    }
}

