package com.aacademy.moonlight.dto.restaurant;

import com.aacademy.moonlight.entity.restaurant.TableRestaurant;
import com.aacademy.moonlight.entity.user.User;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class TableReservationResponse {

    @NotNull
    private LocalDate date;

    @NotNull
    private LocalTime hour;

    @NotNull
    private Integer countPeople;

    @NotNull
    private Double totalPrice;

    @NotNull
    private TableRestaurant tableRestaurant;

    @NotNull
    private Long userId;

    @NotEmpty
    private String userFirstName;

    @NotEmpty
    private String userLastName;

    @NotEmpty
    private String userPhoneNumber;

    @NotEmpty
    private String userEmail;


    private Boolean paymentStatus;

}
