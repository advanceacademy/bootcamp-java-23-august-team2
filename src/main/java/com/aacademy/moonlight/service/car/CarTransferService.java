package com.aacademy.moonlight.service.car;

import com.aacademy.moonlight.dto.car.CarTransferRequest;
import com.aacademy.moonlight.dto.car.CarTransferResponse;
import com.aacademy.moonlight.entity.car.CarTransfer;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CarTransferService {
    CarTransferResponse saveCarTransfer(CarTransferRequest request);
    CarTransfer getCarTransferById(Long id);
    CarTransfer updateCarTransfer(CarTransferRequest request, Long id);
    void deleteCarTransferById(Long id);
    List<CarTransferResponse> allCarReservations();
}
