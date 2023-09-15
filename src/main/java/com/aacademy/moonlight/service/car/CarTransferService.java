package com.aacademy.moonlight.service.car;

import com.aacademy.moonlight.dto.car.CarTransferRequest;
import com.aacademy.moonlight.entity.car.CarTransfer;

public interface CarTransferService {
    CarTransfer saveCarTransfer(CarTransferRequest request);
    CarTransfer getCarTransferById(Long id);
    CarTransfer updateCarTransfer(CarTransferRequest request, Long id);
    void deleteCarTransferById(Long id);
}
