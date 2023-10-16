package com.aacademy.moonlight.service.car.impl;

import com.aacademy.moonlight.converter.car.CarConverter;
import com.aacademy.moonlight.converter.car.CarTransferConverter;
import com.aacademy.moonlight.dto.car.CarResponse;
import com.aacademy.moonlight.dto.car.CarTransferRequest;
import com.aacademy.moonlight.dto.car.CarTransferResponse;
import com.aacademy.moonlight.dto.user.UserResponse;
import com.aacademy.moonlight.entity.car.Car;
import com.aacademy.moonlight.entity.car.CarTransfer;
import com.aacademy.moonlight.entity.user.User;
import com.aacademy.moonlight.entity.user.User;
import com.aacademy.moonlight.entity.car.CarType;
import com.aacademy.moonlight.repository.car.CarRepository;
import com.aacademy.moonlight.repository.car.CarTransferRepository;
import com.aacademy.moonlight.service.car.CarTransferService;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class CarTransferServiceImpl implements CarTransferService {
    private final CarTransferRepository repository;
    private final CarTransferConverter converter;
    private final CarRepository carRepository;
    private final CarConverter carConverter;

    public CarTransferServiceImpl(CarTransferRepository repository, CarTransferConverter converter, CarRepository carRepository, CarConverter carConverter) {
        this.repository = repository;
        this.converter = converter;
        this.carRepository = carRepository;
        this.carConverter = carConverter;
    }

    @Override
    public CarTransferResponse saveCarTransfer(CarTransferRequest request) {
        CarTransfer carTransfer = converter.toTransfer(request);
        List<CarTransfer> transfers = repository.findAll();
        for (CarTransfer transfer : transfers) {
            if (Objects.equals(carTransfer.getCar(), transfer.getCar()) && Objects.equals(carTransfer.getDate(), transfer.getDate())) {
                throw new DuplicateKeyException("Car with id " + carTransfer.getCar().getId() + " has already been reserved on "
                        + carTransfer.getDate());
            }
        }
        CarTransfer savedTransfer = repository.save(carTransfer);
        return converter.toResponse(savedTransfer);
    }

    @Override
    public CarTransfer getCarTransferById(Long id) {
        return repository.findById(id).orElseThrow(
                () -> new RuntimeException("Car transfer with this id not found.")
        );
    }

    @Override
    public CarTransfer updateCarTransfer(CarTransferRequest request, Long id) {
        CarTransfer carTransfer = repository.findById(id).orElseThrow(
                () -> new RuntimeException("Car transfer not found.")
        );
        carTransfer.setDate(request.getDate());
        carTransfer.setPrice(request.getPrice());
        carTransfer.setStatus(request.getStatus());
        carTransfer.setUser(request.getUser());
        carTransfer.setCar(request.getCar());
        return repository.save(carTransfer);
    }

    @Override
    public void deleteCarTransferById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public List<CarTransferResponse> allCarReservations() {
        List<CarTransfer> carList = repository.findAll();
        List<CarTransferResponse> allCarReservations = new ArrayList<>();
        for(CarTransfer carTransfer : carList){
            CarTransferResponse response = converter.toResponse(carTransfer);
            allCarReservations.add(response);
        }
        return allCarReservations;
    }

    @Override
    public List<CarTransferResponse> allCarReservations() {
        List<CarTransfer> carList = repository.findAll();
        List<CarTransferResponse> allCarReservations = new ArrayList<>();
        for(CarTransfer carTransfer : carList){
            CarTransferResponse response = converter.toResponse(carTransfer);
            allCarReservations.add(response);
        }
        return allCarReservations;
    }

    @Override
    public List<CarResponse> getAvailableCarsByDateAndSeat(LocalDate date, int seats, CarType category, String brand) {
        List<CarTransfer> allTransfers = repository.findAll();
        List<Car> allCars = carRepository.findAll();
        List<CarResponse> availableCars = new ArrayList<>();
        for (Car car : allCars) {
            if (car.getCarCategory().getSeats() >= seats
                    && (category == null || car.getCarCategory().getType().equals(category))
                    && (brand == null || car.getBrand().toLowerCase().equalsIgnoreCase(brand))) {
                boolean isCarReserved = false;

                for (CarTransfer transfer : allTransfers) {
                    if (Objects.equals(transfer.getCar(), car) && transfer.getDate().equals(date)) {
                        isCarReserved = true;
                    }
                }
                if (!isCarReserved) {
                    availableCars.add(carConverter.toResponse(car));
                }
            }
        }
        return availableCars;
    }

    @Override
    public List<CarTransferResponse> allCarReservations() {
        List<CarTransfer> carList = repository.findAll();
        List<CarTransferResponse> allCarReservations = new ArrayList<>();
        for (CarTransfer carTransfer : carList) {
            CarTransferResponse response = converter.toResponse(carTransfer);
            allCarReservations.add(response);
        }
        return allCarReservations;
    }
}
