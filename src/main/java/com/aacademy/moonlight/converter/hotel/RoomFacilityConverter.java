package com.aacademy.moonlight.converter.hotel;

import com.aacademy.moonlight.dto.hotel.RoomFacilityRequest;
import com.aacademy.moonlight.entity.hotel.RoomFacility;
import org.springframework.stereotype.Component;

@Component
public class RoomFacilityConverter {

    public RoomFacility toRoomFacility (RoomFacilityRequest request) {

        return RoomFacility.builder()
                .facility(request.getFacility())
                .build();
    }
}
