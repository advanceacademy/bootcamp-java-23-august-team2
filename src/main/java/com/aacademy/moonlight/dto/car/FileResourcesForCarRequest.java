package com.aacademy.moonlight.dto.car;

import com.aacademy.moonlight.entity.car.Car;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class FileResourcesForCarRequest {
    @NotEmpty
    private String imageName;

    @NotNull
    private byte[] photo;

    @NotNull
    private Car car;

}
