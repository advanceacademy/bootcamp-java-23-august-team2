package com.aacademy.moonlight.converter.car;

import com.aacademy.moonlight.dto.car.CarTransferRequest;
import com.aacademy.moonlight.entity.car.CarTransfer;
import org.springframework.stereotype.Component;

@Component
public class CarTransferConverter {
    public CarTransfer toTransfer(CarTransferRequest request){
        return CarTransfer.builder()
                .date(request.getDate())
                .price(request.getPrice())
                .status(request.getStatus())
                .user(request.getUser())
                .car(request.getCar())
                .build();
    }
}
