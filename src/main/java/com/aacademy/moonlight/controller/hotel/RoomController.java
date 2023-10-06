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
@RequestMapping("/api/v1/auth/room")
public class RoomController {

    @Autowired
    RoomService roomService;

    @GetMapping("/find-room-by-id/{id}")
    public ResponseEntity<RoomResponse> findRoomById (@Valid @PathVariable Long id) {
     return ResponseEntity.status(HttpStatus.FOUND).body(roomService.getRoomById(id));
    }

    @GetMapping("/find-room-by-room-number/{roomNumber}")
    public ResponseEntity<List<RoomResponse>> findRoomByRoomNumber(@Valid @PathVariable Integer roomNumber){
        List<RoomResponse>list = roomService.findByRoomNumber(roomNumber);
        return ResponseEntity.status(HttpStatus.FOUND).body(list);
    }

    @GetMapping("/all-rooms")
    public ResponseEntity<List<RoomResponse>> findAllRooms(){
        List<RoomResponse> list = roomService.getAllRooms();
        return ResponseEntity.status(HttpStatus.FOUND).body(list);
    }

    @GetMapping("/get-rooms-by-view/{roomType}")
    public ResponseEntity<List<RoomResponse>> getRoomsByRoomType(@Valid @PathVariable RoomType roomType){
        List<RoomResponse> list = roomService.findByRoomType(roomType);
        return ResponseEntity.status(HttpStatus.FOUND).body(list);
    }

    @GetMapping("/get-rooms-by-view/{roomView}")
    public ResponseEntity<List<RoomResponse>> getRoomsByRoomView(@Valid @PathVariable RoomView roomView){
        List<RoomResponse> list = roomService.findByRoomView(roomView);
        return  ResponseEntity.status(HttpStatus.FOUND).body(list);
    }
    @GetMapping("get-rooms-by-roomPrice/{roomPrice}")
    public ResponseEntity<List<RoomResponse>> getRoomsByRoomPrice (@Valid @PathVariable Long roomPrice){
        List<RoomResponse> list = roomService.findByRoomPrice(roomPrice);
        return ResponseEntity.status(HttpStatus.FOUND).body(list);
    }
}
