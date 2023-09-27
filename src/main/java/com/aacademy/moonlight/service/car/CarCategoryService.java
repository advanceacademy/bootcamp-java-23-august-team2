package com.aacademy.moonlight.service.car;

import com.aacademy.moonlight.dto.car.CarCategoryRequest;
import com.aacademy.moonlight.entity.car.CarCategory;
import com.aacademy.moonlight.entity.car.CarType;

public interface CarCategoryService {
    CarCategory saveCarCategory(CarCategoryRequest request);
    CarCategory getCarCategoryById(Long id);
    CarCategory updateCarCategory(CarCategoryRequest request, Long id);
    void deleteCarCategoryById(Long id);

    CarCategory findByType(CarType type);
}
