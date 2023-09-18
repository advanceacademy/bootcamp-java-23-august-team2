package com.aacademy.moonlight.controller.hotel;

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
    public ResponseEntity<Room> findRoomById (@Valid @PathVariable Long id) {
     return ResponseEntity.status(HttpStatus.FOUND).body(roomService.findByNumber(id));
    }
}
