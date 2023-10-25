package com.aacademy.moonlight.service.hotel;

import com.aacademy.moonlight.dto.hotel.RoomReservationRequest;
import com.aacademy.moonlight.dto.hotel.RoomReservationResponse;
import com.aacademy.moonlight.entity.hotel.RoomReservation;

import java.util.List;
import java.util.Optional;

public interface RoomReservationService {

    RoomReservation createRoomReservation(RoomReservationRequest request);

    void deleteRoomReservation(Long id);

    RoomReservation updateRoomReservation(Long id, RoomReservationRequest request);

    Optional<RoomReservation> findRoomReservationById(Long id);
    List<RoomReservationResponse> getAllRoomReservations();

}
