package com.aacademy.moonlight.dto.restaurant;

import com.aacademy.moonlight.custom_deserialization.CustomHourDeserializer;
import com.aacademy.moonlight.entity.restaurant.TableRestaurant;
import com.aacademy.moonlight.entity.user.User;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TableReservationRequest {
    @FutureOrPresent(message = "Date must be in the future")
    @NotNull
    private LocalDate date;

    @JsonDeserialize(using = CustomHourDeserializer.class)
    @NotNull
    private LocalTime hour;

    @Min(value = 1)
    @Max(value = 11)
    @NotNull(message = "Please select the number of people")
    private Integer countPeople;

    @Min(value = 10)
    private Double totalPrice;

    @NotNull
    private TableRestaurant tableRestaurant;

    private User user;

    private Boolean paymentStatus;
}
