package com.aacademy.moonlight.service.car;

import com.aacademy.moonlight.dto.car.CarRequest;
import com.aacademy.moonlight.dto.car.CarResponse;
import com.aacademy.moonlight.entity.car.Car;
import com.aacademy.moonlight.entity.car.CarCategory;
import com.aacademy.moonlight.entity.car.CarType;

import java.util.List;

public interface CarService {
    Car saveCar(CarRequest request);
    Car getCarById (Long id);
    Car updateCar(CarRequest request, Long id);
    void deleteCarById(Long id);
    CarResponse getCarResponseById(Long id);
    List<CarResponse> getAllCars();
    List<CarResponse> getCarsByCategory(Long categoryId);
    List<CarResponse> getCarsByYear(int year);
    List<CarResponse> getCarsByModel(String model);
    List<CarResponse> getCarsByBrand(String brand);
    List<CarResponse> getCarsByType(String carType);
    List<CarResponse> getCarsBySeats(int seats);





}
