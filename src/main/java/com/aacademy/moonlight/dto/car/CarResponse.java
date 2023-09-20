package com.aacademy.moonlight.dto.car;

import com.aacademy.moonlight.entity.car.CarCategory;
import com.aacademy.moonlight.entity.car.CarType;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class CarResponse {

        private String brand;

        private String model;

        private Integer manufacturingYear;

        private CarCategory carCategory;

        private CarType type;
}
