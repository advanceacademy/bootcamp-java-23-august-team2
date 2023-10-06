package com.aacademy.moonlight.converter.hotel;

import com.aacademy.moonlight.dto.hotel.RoomRequest;
import com.aacademy.moonlight.dto.hotel.RoomResponse;
import com.aacademy.moonlight.entity.hotel.Room;
import com.aacademy.moonlight.entity.hotel.RoomFacility;
import com.aacademy.moonlight.repository.hotel.RoomFacilityRepository;
import com.aacademy.moonlight.repository.hotel.RoomRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class RoomConverter {

    private final RoomRepository repository;
    private final RoomFacilityRepository roomFacilityRepository;

    public Room createRoom (Integer roomId, RoomRequest request){

        /*
        List<Long> facilityIds = request.getFacilityIds();
        if (facilityIds != null && !facilityIds.isEmpty()) {
            List<RoomFacility> facilities = roomFacilityRepository.findAllById(facilityIds);
            room.setFacilities(facilities);

         */

        List<RoomFacility> facilityList = roomFacilityRepository.findAll();

        return  Room.builder()
                .roomNumber(request.getRoomNumber())
                .price(request.getPrice())
                .type(request.getType())
                .view(request.getView())
                .facilities(facilityList)
                .area(request.getArea())
                .build();

    }
    public RoomResponse toRoomResponse(Room room){
        return new RoomResponse(room.getId(), room.getRoomNumber(), room.getPrice(), room.getArea(), room.getType(),
                room.getView(),room.getFacilities(), room.getType().getRoomCapacity());
    }

}
