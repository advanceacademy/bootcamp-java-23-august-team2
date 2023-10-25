package com.aacademy.moonlight.controller.car;

import com.aacademy.moonlight.dto.car.CarResponse;
import com.aacademy.moonlight.dto.car.CarTransferRequest;
import com.aacademy.moonlight.dto.car.CarTransferResponse;
import com.aacademy.moonlight.entity.car.Car;
import com.aacademy.moonlight.entity.car.CarTransfer;
import com.aacademy.moonlight.entity.car.CarType;
import com.aacademy.moonlight.entity.user.User;
import com.aacademy.moonlight.service.car.CarService;
import com.aacademy.moonlight.service.car.CarTransferService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/v1/client/car-transfer")
public class CarTransferController {
    @Autowired
    CarTransferService service;
    @Autowired
    CarService carService;

    @PostMapping(path = "/create-reservation", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CarTransferResponse> createTransfer(@RequestBody CarTransferRequest request) {
        Long carId = request.getCar().getId();

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        request.setUser((User) auth.getPrincipal());

        Car car = carService.getCarById(carId);

        if (car == null) {
            throw new EntityNotFoundException("Car with id " + carId + " was not found.");
        }
        request.setCar(car);
        request.setPrice(request.getCar().getCarCategory().getPrice());

        return ResponseEntity.status(HttpStatus.CREATED).body(service.saveCarTransfer(request));
    }

    @GetMapping(path = "/find-available-cars")
    public ResponseEntity<List<CarResponse>> getAvailableCars(
            @RequestParam("date") LocalDate date,
            @RequestParam("seats") int seats,
            @RequestParam(value = "category", required = false) CarType category,
            @RequestParam(value = "brand", required = false) String brand) {
        return ResponseEntity.ok(service.getAvailableCarsByDateAndSeat(date, seats, category, brand));
    }

    @GetMapping(path = "/find-user-transfers")
    public ResponseEntity<List<CarTransferResponse>> getAllUserTransfers(){

        return ResponseEntity.status(HttpStatus.FOUND).body(service.getTransfersByUser());
    }

}
