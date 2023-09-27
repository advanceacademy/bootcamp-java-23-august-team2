package com.aacademy.moonlight.service.car.impl;

import com.aacademy.moonlight.converter.car.CarConverter;
import com.aacademy.moonlight.dto.car.CarRequest;
import com.aacademy.moonlight.dto.car.CarResponse;
import com.aacademy.moonlight.entity.car.Car;
import com.aacademy.moonlight.entity.car.CarType;
import com.aacademy.moonlight.repository.car.CarCategoryRepository;
import com.aacademy.moonlight.repository.car.CarRepository;
import com.aacademy.moonlight.service.car.CarService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class CarServiceImpl implements CarService {
    private final CarConverter converter;
    private final CarRepository repository;

    private final CarCategoryRepository carCategoryRepository;

    public CarServiceImpl(CarConverter converter, CarRepository repository, CarCategoryRepository carCategoryRepository) {
        this.converter = converter;
        this.repository = repository;
        this.carCategoryRepository = carCategoryRepository;
    }

    @Override
    public Car saveCar(CarRequest request) {
        Car car = converter.toCar(request);
        return repository.save(car);
    }

    @Override
    public Car getCarById(Long id) {
        return repository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Car with id= "+ id +" not found.")
        );
    }

    @Override
    public Car updateCar(CarRequest request, Long id) {
        Car car = repository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Car with id= "+ id +" not found.")
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
        if (cars.isEmpty()) {
          throw  new EntityNotFoundException("There are no cars in category: "  + carCategoryRepository.findById(categoryId));
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
        if (cars.isEmpty()) {
            throw  new EntityNotFoundException("There are no cars with manufacturing year: "  + year);
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
        if (cars.isEmpty()) {
            throw  new EntityNotFoundException("There are no " + model + " in our catalog" );
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
        if (cars.isEmpty()) {
            throw  new EntityNotFoundException("There are no " + brand + " in our catalog" );
        }
        return cars;
    }

    @Override
    public List<CarResponse> getCarsByType(String carType) {
        CarType type;
        if (carType.equalsIgnoreCase("van")){
            type = CarType.VAN;
        }
        else if (carType.equalsIgnoreCase("sedan")){
            type = CarType.SEDAN;
        }
         else if (carType.equalsIgnoreCase("sport")){
            type = CarType.SPORT;
        }
         else {
             throw new EntityNotFoundException("Our catalog has only Sedans, Sport Cars and Vans");
        }
        List<CarResponse> cars = new ArrayList<>();

        for (Car car : repository.findAll()) {
            if (car.getCarCategory().getType() == type) {
                cars.add(converter.toResponse(car));
            }
        }
        if (cars.isEmpty()) {
            throw  new EntityNotFoundException(carType.toString() + " was not found " );
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
        if (cars.isEmpty()) {
            throw  new EntityNotFoundException("Cars with " + seats + " seats were not found" );
        }
        return cars;
    }
}
