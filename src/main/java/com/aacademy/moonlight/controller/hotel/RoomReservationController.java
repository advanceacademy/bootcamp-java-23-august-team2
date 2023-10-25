package com.aacademy.moonlight.controller.hotel;

import com.aacademy.moonlight.converter.hotel.RoomReservationConverter;
import com.aacademy.moonlight.dto.hotel.RoomReservationRequest;

import com.aacademy.moonlight.dto.hotel.RoomReservationResponse;
import com.aacademy.moonlight.entity.hotel.RoomReservation;
import com.aacademy.moonlight.service.hotel.RoomReservationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1/client/room-reservation")
public class RoomReservationController {

    @Autowired
    RoomReservationService service;
    @Autowired
    RoomReservationConverter converter;

    @PostMapping("/create-room-reservation")
    public ResponseEntity<RoomReservationResponse> createRoomReservation(@Valid @RequestBody RoomReservationRequest request){
        RoomReservation reservation = service.createRoomReservation(request);
        RoomReservationResponse response = converter.toResponse(reservation);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{roomId}/get-all-reservations")
    public ResponseEntity<RoomReservation> getAllReservationsByRoomId (@PathVariable Long id){

        RoomReservation allReservations = service.findRoomReservationById(id).orElseThrow();
        return ResponseEntity.status(HttpStatus.OK).body(allReservations);
    }

    @DeleteMapping("/{id}/delete-reservation")
    public ResponseEntity<String> deleteReservation(@PathVariable Long id){
        service.deleteRoomReservation(id);
        return ResponseEntity.status(HttpStatus.OK).body("Reservation has been deleted");
    }
    @GetMapping(path = "/find-user-reservations")
    public ResponseEntity<List<RoomReservationResponse>> getAllUserReservations(){
        return ResponseEntity.status(HttpStatus.FOUND).body(service.getReservationsByUser());
    }

 //   @PutMapping("/updating-reservation")
}
