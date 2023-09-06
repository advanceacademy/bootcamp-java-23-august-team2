package com.aacademy.moonlight.service.hotel;

import com.aacademy.moonlight.dto.hotel.RoomReservationRequest;
import com.aacademy.moonlight.entity.hotel.RoomReservation;

import java.util.Optional;

public interface RoomReservationService {

    RoomReservation createRoomReservation(RoomReservationRequest request);

    void deleteRoomReservation(Long id);

    RoomReservation updateRoomReservation(Long id, RoomReservationRequest request);

    Optional<RoomReservation> findRoomReservationById(Long id);

}
