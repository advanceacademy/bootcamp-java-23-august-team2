package com.aacademy.moonlight.service.hotel;

import com.aacademy.moonlight.dto.hotel.RoomFacilityRequest;
import com.aacademy.moonlight.entity.hotel.RoomFacility;

import java.util.Optional;

public interface RoomFacilityService {

    RoomFacility saveRoomFacility(RoomFacilityRequest roomFacilityRequest);

    Optional<RoomFacility> getRoomFacility(Long id);

    void deleteRoomFacility(Long id);
}
