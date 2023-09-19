package com.aacademy.moonlight.controller.hotel;

import com.aacademy.moonlight.dto.hotel.RoomResponse;
import com.aacademy.moonlight.entity.hotel.Room;
import com.aacademy.moonlight.entity.hotel.RoomType;
import com.aacademy.moonlight.entity.hotel.RoomView;
import com.aacademy.moonlight.service.hotel.RoomService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/room")
public class RoomController {

    @Autowired
    RoomService roomService;

    @GetMapping("/{id}/find-room")
    public ResponseEntity<RoomResponse> findRoomById (@Valid @PathVariable Long id) {
     return ResponseEntity.status(HttpStatus.FOUND).body(roomService.getRoomById(id));
    }

    @GetMapping("/api/v1/all-rooms")
    public ResponseEntity<List<RoomResponse>> findAllRooms(){
        List<RoomResponse> list = roomService.getAllRooms();
        return ResponseEntity.status(HttpStatus.FOUND).body(list);
    }

    @GetMapping("{roomType}/api/v1/get-rooms-by-view")
    public ResponseEntity<List<RoomResponse>> getRoomsByRoomType(@Valid @PathVariable RoomType roomType){
        List<RoomResponse> list = roomService.findByRoomType(roomType);
        return ResponseEntity.status(HttpStatus.FOUND).body(list);
    }

    @GetMapping("{roomView}/api/v1/get-rooms-by-view")
    public ResponseEntity<List<RoomResponse>> getRoomsByRoomView(@Valid @PathVariable RoomView roomView){
        List<RoomResponse> list = roomService.findByRoomView(roomView);
        return  ResponseEntity.status(HttpStatus.FOUND).body(list);
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
