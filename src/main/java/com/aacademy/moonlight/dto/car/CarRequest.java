package com.aacademy.moonlight.dto.car;


import com.aacademy.moonlight.entity.car.CarCategory;
import com.aacademy.moonlight.entity.car.CarType;
import com.aacademy.moonlight.entity.car.FileResourcesForCar;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CarRequest {

    @NotEmpty
    private String brand;

    @NotEmpty
    private String model;

    @Digits(integer = 4, fraction = 0, message = "Year must be a 4-digit number")
    private Integer manufacturingYear;

    @NotNull
    private CarCategory carCategory;

    private List<FileResourcesForCar> fileResourcesForCar;
}
