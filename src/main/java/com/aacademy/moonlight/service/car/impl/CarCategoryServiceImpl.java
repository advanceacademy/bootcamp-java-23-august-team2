package com.aacademy.moonlight.service.car.impl;

import com.aacademy.moonlight.converter.car.CarCategoryConverter;
import com.aacademy.moonlight.dto.car.CarCategoryRequest;
import com.aacademy.moonlight.entity.car.CarCategory;
import com.aacademy.moonlight.repository.car.CarCategoryRepository;
import com.aacademy.moonlight.service.car.CarCategoryService;
import jakarta.persistence.EntityNotFoundException;

import java.util.List;
import java.util.Objects;

@Service
public class CarCategoryServiceImpl implements CarCategoryService {
    private final CarCategoryRepository repository;
    private final CarCategoryConverter converter;

    public CarCategoryServiceImpl(CarCategoryRepository repository, CarCategoryConverter converter) {
        this.repository = repository;
        this.converter = converter;
    }

    @Override
    public CarCategory saveCarCategory(CarCategoryRequest request) {
        CarCategory carCategory = converter.toCarCategory(request);
        return repository.save(carCategory);
    }

    @Override
    public CarCategory getCarCategoryById(Long id) {
        return repository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Car category with id= "+id +" not found.")
        );
    }

    @Override
    public CarCategory updateCarCategory(CarCategoryRequest request, Long id) {
        CarCategory carCategory = repository.findById(id).orElseThrow(
                () -> new RuntimeException("Car category not found.")
        );
        carCategory.setType(request.getType());
        carCategory.setSeats(request.getSeats());
        carCategory.setPrice(request.getPrice());

        return repository.save(carCategory);
    }

    @Override
    public void deleteCarCategoryById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public CarCategory findByType(CarType type) {
        List<CarCategory> carCategories = repository.findAll();
        CarCategory currentCategory = null;

        for (CarCategory carCategory : carCategories){
            if (Objects.equals(carCategory.getType(), type)){
                currentCategory = carCategory;
            }
        }
        return currentCategory;
    }
}
