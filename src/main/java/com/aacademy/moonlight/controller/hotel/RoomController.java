package com.aacademy.moonlight.controller.hotel;
import com.aacademy.moonlight.dto.hotel.RoomRequest;
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


    @PostMapping("/create-room")
    public ResponseEntity<Room> registerRoom(@Valid @RequestBody RoomRequest request){

        Room savedRoom = roomService.saveRoom(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedRoom);
    }

    @GetMapping("/{id}/find-room")
    public ResponseEntity<Room> findRoomById (@Valid @PathVariable Long id) {
     return ResponseEntity.status(HttpStatus.FOUND).body(roomService.findByNumber(id));
    }

    @DeleteMapping("/{id}/delete-room")
    public ResponseEntity<String> deleteRoom(@Valid @PathVariable Long id){
        roomService.deleteRoom(id);
        return ResponseEntity.status(HttpStatus.GONE).body("Room was deleted");
    }

    @PutMapping("/{id}/update-room")
    public ResponseEntity<Room> updateRoom (@PathVariable Long id,
                                           @Valid @RequestBody RoomRequest request){
        Room updatedRoom = roomService.findByNumber(id);
        updatedRoom = roomService.saveRoom(request);
        return ResponseEntity.status(HttpStatus.OK).body(updatedRoom);
    }



}
