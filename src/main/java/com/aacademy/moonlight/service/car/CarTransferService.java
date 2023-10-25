package com.aacademy.moonlight.service.car;

import com.aacademy.moonlight.dto.car.CarResponse;
import com.aacademy.moonlight.dto.car.CarTransferRequest;
import com.aacademy.moonlight.dto.car.CarTransferResponse;
import com.aacademy.moonlight.entity.car.CarTransfer;
import com.aacademy.moonlight.entity.car.CarType;
import com.aacademy.moonlight.entity.user.User;
import org.springframework.stereotype.Service;

import java.util.List;

import java.time.LocalDate;



@Service
public interface CarTransferService {
    CarTransferResponse saveCarTransfer(CarTransferRequest request);
    CarTransfer getCarTransferById(Long id);
    CarTransfer updateCarTransfer(CarTransferRequest request, Long id);
    void deleteCarTransferById(Long id);

    List<CarResponse> getAvailableCarsByDateAndSeat(LocalDate date, int seats, CarType category, String brand);
    List<CarTransferResponse> allCarReservations();
    List<CarTransferResponse> getTransfersByUser();

}
