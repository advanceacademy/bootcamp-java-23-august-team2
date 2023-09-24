package com.aacademy.moonlight.converter.car;

import com.aacademy.moonlight.dto.car.CarCategoryRequest;
import com.aacademy.moonlight.entity.car.CarCategory;
import org.springframework.stereotype.Component;

@Component
public class CarCategoryConverter {

    public CarCategory toCarCategory(CarCategoryRequest request){
        return CarCategory.builder()
                .type(request.getType())
                .seats(request.getSeats())
                .price(request.getPrice())
                .build();
    }
}
