package com.aacademy.moonlight.controller.admin;
import com.aacademy.moonlight.dto.car.CarTransferResponse;
import com.aacademy.moonlight.dto.hotel.RoomReservationResponse;
import com.aacademy.moonlight.dto.restaurant.TableReservationResponse;
import com.aacademy.moonlight.dto.user.UserResponse;
import com.aacademy.moonlight.entity.hotel.RoomReservation;
import com.aacademy.moonlight.service.hotel.RoomReservationService;
import com.aacademy.moonlight.service.car.CarTransferService;
import com.aacademy.moonlight.service.restaurant.TableReservationService;
import com.aacademy.moonlight.service.user.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/admin")
public class AdminController {

    @Autowired
    UserService userService;

    @Autowired
    RoomReservationService roomReservationService;

    @Autowired
    TableReservationService tableReservationService;

    @Autowired
    CarTransferService carTransferService;

    @GetMapping(path = "/{id}")
    public ResponseEntity<UserResponse> getUser(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.findUserById(id));
    }

    @GetMapping(path = "/user-by-email/{mail}")
    public ResponseEntity<UserResponse> getUserByEmail(@PathVariable String mail) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.findUserByEmail(mail));
    }

    @GetMapping(path = "/user-by-firstName/{name}")
    public ResponseEntity<List<UserResponse>> getUserByFirstName(@PathVariable String name) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.findUsersByFirstName(name));
    }

    @GetMapping(path = "/user-by-lastName/{name}")
    public ResponseEntity<List<UserResponse>> getUserByLastName(@PathVariable String name) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.findUsersByLastName(name));
    }

    @GetMapping(path = "/user-by-phoneNumber/{number}")
    public ResponseEntity<UserResponse> getUserByPhoneNumber(@PathVariable String number) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.findUserByPhoneNumber(number));
    }

    @GetMapping(path = "/getAllRoomReservations")
    public ResponseEntity<List<RoomReservationResponse>> getAllRoomReservations(){
        return ResponseEntity.status(HttpStatus.OK).body(roomReservationService.getAllRoomReservations());
    }

    @GetMapping(path = "/getAllTableReservations")
    public ResponseEntity<List<TableReservationResponse>> getAllTableReservations(){
        return ResponseEntity.status(HttpStatus.OK).body(tableReservationService.getAllTableReservations());
    }

    @GetMapping(path = "/allCarTransfers")
    @Operation(summary = "Get all cars", description = "Returns all cars")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "All cars were successfully found"),
            @ApiResponse(responseCode = "404", description = "No car was found")
    })
    public ResponseEntity<List<CarTransferResponse>> getAllCarTransfers() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(carTransferService.allCarReservations());
    }

}
