package com.aacademy.moonlight.service.car;

import com.aacademy.moonlight.dto.car.CarRequest;
import com.aacademy.moonlight.entity.car.Car;

public interface CarService {
    Car saveCar(CarRequest request);
    Car getCarById (Long id);
    Car updateCar(CarRequest request, Long id);
    void deleteCarById(Long id);



}
