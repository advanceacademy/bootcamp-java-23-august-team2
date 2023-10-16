package com.aacademy.moonlight.service.car;

import com.aacademy.moonlight.dto.car.CarResponse;
import com.aacademy.moonlight.dto.car.CarTransferRequest;
import com.aacademy.moonlight.dto.car.CarTransferResponse;
import com.aacademy.moonlight.entity.car.CarTransfer;
import com.aacademy.moonlight.entity.car.CarType;
import org.springframework.stereotype.Service;


import java.time.LocalDate;

import java.util.List;

@Service
public interface CarTransferService {
    CarTransferResponse saveCarTransfer(CarTransferRequest request);
    CarTransfer getCarTransferById(Long id);
    CarTransfer updateCarTransfer(CarTransferRequest request, Long id);
    void deleteCarTransferById(Long id);
  
    List<CarResponse> getAvailableCarsByDateAndSeat(LocalDate date, int seats, CarType category, String brand);

}
