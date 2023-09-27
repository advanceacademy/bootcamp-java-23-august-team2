package com.aacademy.moonlight.service.car.impl;

import com.aacademy.moonlight.converter.car.CarConverter;
import com.aacademy.moonlight.dto.car.CarRequest;
import com.aacademy.moonlight.entity.car.Car;
import com.aacademy.moonlight.repository.car.CarRepository;
import com.aacademy.moonlight.service.car.CarService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class CarServiceImpl implements CarService {
    private final CarConverter converter;
    private final CarRepository repository;

    public CarServiceImpl(CarConverter converter, CarRepository repository) {
        this.converter = converter;
        this.repository = repository;
    }

    @Override
    public Car saveCar(CarRequest request) {
        Car car = converter.toCar(request);
        return repository.save(car);
    }

    @Override
    public Car getCarById(Long id) {
        return repository.findById(id).orElseThrow(
                () -> new RuntimeException("Car with this id not found")
        );
    }

    @Override
    public Car updateCar(CarRequest request, Long id) {
        Car car = repository.findById(id).orElseThrow(
                () -> new RuntimeException("Car not found")
        );
        car.setBrand(request.getBrand());
        car.setModel(request.getModel());
        car.setManufacturingYear(request.getManufacturingYear());
        car.setCarCategory(request.getCarCategory());
        car.setFileResourcesForCar(request.getFileResourcesForCar());

        return repository.save(car);
    }

    @Override
    public void deleteCarById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Car findCarByBrandAndModel(String brand, String model) {
        List<Car> cars = repository.findAll();
        Car currentCar = null;

        for (Car car : cars){
            if (Objects.equals(car.getBrand(), brand) && Objects.equals(car.getModel(), model)){
                currentCar = car;
            }
        }
        return currentCar;
    }
}
