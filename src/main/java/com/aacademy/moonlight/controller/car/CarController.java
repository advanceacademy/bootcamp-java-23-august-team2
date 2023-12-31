package com.aacademy.moonlight.controller.car;

import com.aacademy.moonlight.dto.car.CarResponse;
import com.aacademy.moonlight.service.car.CarService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/auth/car")

public class CarController {

    @Autowired
    CarService carService;

    @GetMapping(path = "/{id}")
    @Operation(summary = "Get a car by id", description = "Returns a car as per id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Car with this id was successfully found"),
            @ApiResponse(responseCode = "404", description = "Car with this id was not found")
    })
    public ResponseEntity<CarResponse> getCar(@PathVariable("id")
                                              @Parameter(example = "2") Long id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(carService.getCarResponseById(id));
    }

    @GetMapping(path = "/car-brand/{brand}")
    @Operation(summary = "Get a car by brand", description = "Returns a car as per brand")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Car with this brand was successfully found"),
            @ApiResponse(responseCode = "404", description = "Car with this brand was not found")
    })
    public ResponseEntity<List<CarResponse>> getCarByBrand(@PathVariable("brand")
                                                           @Parameter(example = "Ferrari") String brand) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(carService.getCarsByBrand(brand));
    }

    @GetMapping(path = "/car-model/{model}")
    @Operation(summary = "Get a car by model", description = "Returns a car as per model")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Car with this model was successfully found"),
            @ApiResponse(responseCode = "404", description = "Car with this model was not found")
    })
    public ResponseEntity<List<CarResponse>> getCarByModel(@PathVariable("model")
                                                           @Parameter(example = "F8") String model) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(carService.getCarsByModel(model));
    }

    @GetMapping(path = "/all")
    @Operation(summary = "Get all cars", description = "Returns all cars")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "All cars were successfully found"),
            @ApiResponse(responseCode = "404", description = "No car was found")
    })
    public ResponseEntity<List<CarResponse>> getAllCars() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(carService.getAllCars());
    }

    @GetMapping(path = "/car-year/{year}")
    @Operation(summary = "Get a car by year of manufacturing ", description = "Returns a car as per year of manufacturing")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Car with this year was successfully found"),
            @ApiResponse(responseCode = "404", description = "Car with this year was not found")
    })
    public ResponseEntity<List<CarResponse>> getCarByYear(@PathVariable("year")
                                                          @Parameter(example = "2021") int year) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(carService.getCarsByYear(year));
    }

    @GetMapping(path = "/car-seats/{seats}")
    @Operation(summary = "Get a car by number of seats", description = "Returns a car as per number of seats")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Car with this number of seats was successfully found"),
            @ApiResponse(responseCode = "404", description = "Car with this number of seats was not found")
    })
    public ResponseEntity<List<CarResponse>> getCarBySeats(@PathVariable("seats")
                                                           @Parameter(example = "4") int seats) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(carService.getCarsBySeats(seats));
    }

    @GetMapping(path = "/car-type/{type}")
    @Operation(summary = "Get a car by type", description = "Returns a car as per type")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Car with this type was successfully found"),
            @ApiResponse(responseCode = "404", description = "Car with this type was not found")
    })
    public ResponseEntity<List<CarResponse>> getCarBySeats(@PathVariable("type")
                                                           @Parameter(example = "SEDAN") String type) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(carService.getCarsByType(type));
    }

    @GetMapping(path = "/car-category/{id}")
    @Operation(summary = "Get a car by type", description = "Returns a car as per type")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Car with this category id was successfully found"),
            @ApiResponse(responseCode = "404", description = "Car with this category id was not found")
    })
    public ResponseEntity<List<CarResponse>> getCarByCategory(@PathVariable("id")
                                                              @Parameter(example = "1") Long id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(carService.getCarsByCategory(id));
    }

}
