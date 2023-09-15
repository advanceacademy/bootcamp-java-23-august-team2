package com.aacademy.moonlight.service.car.impl;

import com.aacademy.moonlight.converter.car.CarTransferConverter;
import com.aacademy.moonlight.dto.car.CarTransferRequest;
import com.aacademy.moonlight.entity.car.CarTransfer;
import com.aacademy.moonlight.repository.car.CarTransferRepository;
import com.aacademy.moonlight.service.car.CarTransferService;

public class CarTransferServiceImpl implements CarTransferService {
    private final CarTransferRepository repository;
    private final CarTransferConverter converter;

    public CarTransferServiceImpl(CarTransferRepository repository, CarTransferConverter converter) {
        this.repository = repository;
        this.converter = converter;
    }

    @Override
    public CarTransfer saveCarTransfer(CarTransferRequest request) {
        CarTransfer carTransfer = converter.toTransfer(request);
        return repository.save(carTransfer);
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
}
