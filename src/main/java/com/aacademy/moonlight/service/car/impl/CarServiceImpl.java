package com.aacademy.moonlight.service.car.impl;

import com.aacademy.moonlight.converter.car.CarConverter;
import com.aacademy.moonlight.dto.car.CarRequest;
import com.aacademy.moonlight.dto.car.CarResponse;
import com.aacademy.moonlight.entity.car.Car;
import com.aacademy.moonlight.entity.car.CarCategory;
import com.aacademy.moonlight.entity.car.CarType;
import com.aacademy.moonlight.repository.car.CarRepository;
import com.aacademy.moonlight.service.car.CarService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
        car.setType(request.getType());
        car.setFileResourcesForCar(request.getFileResourcesForCar());

        return repository.save(car);
    }

    @Override
    public void deleteCarById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public CarResponse getCarResponseById(Long id) {
        return converter.toResponse(getCarById(id));
    }

    @Override
    public List<CarResponse> getAllCars() {

        List<CarResponse> cars = new ArrayList<>();

        for (Car car : repository.findAll()) {
            cars.add(converter.toResponse(car));
        }

        return cars;
    }

    @Override
    public List<CarResponse> getCarsByCategory(Long categoryId) {

        List<CarResponse> cars = new ArrayList<>();

        for (Car car : repository.findAll()) {
            if (Objects.equals(categoryId, car.getCarCategory().getId())) {
                cars.add(converter.toResponse(car));
            }
        }
        return cars;
    }

    @Override
    public List<CarResponse> getCarsByYear(int year) {

        List<CarResponse> cars = new ArrayList<>();

        for (Car car : repository.findAll()) {
            if (car.getManufacturingYear() == year) {
                cars.add(converter.toResponse(car));
            }
        }
        return cars;
    }

    @Override
    public List<CarResponse> getCarsByModel(String model) {
        List<CarResponse> cars = new ArrayList<>();

        for (Car car : repository.findAll()) {
            if (car.getModel().equals(model)) {
                cars.add(converter.toResponse(car));
            }
        }
        return cars;
    }

    @Override
    public List<CarResponse> getCarsByBrand(String brand) {

        List<CarResponse> cars = new ArrayList<>();

        for (Car car : repository.findAll()) {
            if (car.getBrand().equals(brand)) {
                cars.add(converter.toResponse(car));
            }
        }
        return cars;
    }

    @Override
    public List<CarResponse> getCarsByType(CarType carType) {
        List<CarResponse> cars = new ArrayList<>();

        for (Car car : repository.findAll()) {
            if (car.getType() == carType) {
                cars.add(converter.toResponse(car));
            }
        }
        return cars;
    }

    @Override
    public List<CarResponse> getCarsBySeats(int seats) {
        List<CarResponse> cars = new ArrayList<>();

        for (Car car : repository.findAll()) {
            if (car.getCarCategory().getSeats() == seats) {
                cars.add(converter.toResponse(car));
            }
        }
        return cars;
    }
}
