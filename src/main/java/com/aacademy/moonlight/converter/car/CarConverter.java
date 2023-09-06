package com.aacademy.moonlight.converter.car;


import com.aacademy.moonlight.dto.car.CarRequest;
import com.aacademy.moonlight.entity.car.Car;
import org.springframework.stereotype.Component;

@Component
public class CarConverter {

    public Car toCar(CarRequest request){
        return Car.builder()
                .brand(request.getBrand())
                .model(request.getModel())
                .manufacturingYear(request.getManufacturingYear())
                .carCategory(request.getCarCategory())
                .build();
    }
}
