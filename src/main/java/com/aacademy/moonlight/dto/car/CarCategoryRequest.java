package com.aacademy.moonlight.dto.car;

import com.aacademy.moonlight.entity.car.CarType;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CarCategoryRequest {

    @NotNull()
    private CarType type;

    @NotNull
    @Min(value = 2)
    private Integer seats;

    @NotNull(message = "Car should have price")
    @Min(value = 600)
    private Double price;
}
