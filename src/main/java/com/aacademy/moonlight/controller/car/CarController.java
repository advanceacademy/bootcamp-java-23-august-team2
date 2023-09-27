package com.aacademy.moonlight.controller.car;

import com.aacademy.moonlight.dto.car.CarResponse;
import com.aacademy.moonlight.entity.car.CarCategory;
import com.aacademy.moonlight.entity.car.CarType;
import com.aacademy.moonlight.service.car.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/auth/car") //"/api/v1/auth/**"
public class CarController {
    //Get Cars by category
    @Autowired
    CarService carService;

    @GetMapping(path = "/{id}")
    public ResponseEntity<CarResponse> getCar(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.FOUND)
                .body(carService.getCarResponseById(id));
    }

    @GetMapping(path = "/car-brand/{brand}")
    public ResponseEntity<List<CarResponse>> getCarByBrand(@PathVariable String brand) {
        return ResponseEntity.status(HttpStatus.FOUND)
                .body(carService.getCarsByBrand(brand));
    }

    @GetMapping(path = "/car-model/{model}")
    public ResponseEntity<List<CarResponse>> getCarByModel(@PathVariable String model) {
        return ResponseEntity.status(HttpStatus.FOUND)
                .body(carService.getCarsByModel(model));
    }

    @GetMapping(path = "/all")
    public ResponseEntity<List<CarResponse>> getAllCars() {
        return ResponseEntity.status(HttpStatus.FOUND)
                .body(carService.getAllCars());
    }

    @GetMapping(path = "/car-year/{year}")
    public ResponseEntity<List<CarResponse>> getCarByYear(@PathVariable int year) {
        return ResponseEntity.status(HttpStatus.FOUND)
                .body(carService.getCarsByYear(year));
    }

    @GetMapping(path = "/car-seats/{seats}")
    public ResponseEntity<List<CarResponse>> getCarBySeats(@PathVariable int seats) {
        return ResponseEntity.status(HttpStatus.FOUND)
                .body(carService.getCarsBySeats(seats));
    }

    @GetMapping(path = "/car-type/{type}")
    public ResponseEntity<List<CarResponse>> getCarBySeats(@PathVariable String type) {
        return ResponseEntity.status(HttpStatus.FOUND)
                .body(carService.getCarsByType(type));
    }

    @GetMapping(path = "/car-category/{id}")
    public ResponseEntity<List<CarResponse>> getCarByCategory(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.FOUND)
                .body(carService.getCarsByCategory(id));
    }

}
