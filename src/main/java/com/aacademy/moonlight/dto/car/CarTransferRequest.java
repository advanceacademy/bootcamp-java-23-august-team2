package com.aacademy.moonlight.dto.car;

import com.aacademy.moonlight.entity.car.Car;
import com.aacademy.moonlight.entity.user.User;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CarTransferRequest {

    @NotNull
    @Future(message = "The transfer can not be booked in the past moment")
    private LocalDate date;

    @NotNull
    @Min(value = 600)
    private Double price;

    private boolean status;

    @NotNull
    private User user;

    @NotNull
    private Car car;

    public boolean getStatus() {
        return false;
    }
}
