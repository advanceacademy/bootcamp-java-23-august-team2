package com.aacademy.moonlight.controller.hotel;

import com.aacademy.moonlight.dto.hotel.RoomResponse;
import com.aacademy.moonlight.entity.hotel.Room;
import com.aacademy.moonlight.service.hotel.RoomService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/room")
public class RoomController {

    @Autowired
    RoomService roomService;

    @GetMapping("/{id}/find-room")
    public ResponseEntity<RoomResponse> findRoomById (@Valid @PathVariable Long id) {
     return ResponseEntity.status(HttpStatus.FOUND).body(roomService.getRoomById(id));
    }

    //TODO
    //Get by ID
    //
    //Get by Room number
    //
    //List of all rooms
    //
    //List by Room type
    //
    //List by room view
    //
    //List by room People
    //
    //List by room price
}
