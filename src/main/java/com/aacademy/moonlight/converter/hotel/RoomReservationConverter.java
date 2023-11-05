package com.aacademy.moonlight.converter.hotel;

import com.aacademy.moonlight.dto.hotel.RoomReservationResponse;
import com.aacademy.moonlight.entity.hotel.RoomReservation;
import org.springframework.stereotype.Component;

@Component
public class RoomReservationConverter {

    public RoomReservationResponse toResponse(RoomReservation savedReservation){
        return new RoomReservationResponse(
                savedReservation.getRoom().getId(),
                savedReservation.getStartDate(),
                savedReservation.getEndDate(),
                savedReservation.getTotalPrice()
        );
    }
}
