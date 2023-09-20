package com.aacademy.moonlight.dto.restaurant;

import com.aacademy.moonlight.entity.restaurant.TableRestaurant;
import com.aacademy.moonlight.entity.user.User;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@Builder

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
    private User user;

    private Boolean paymentStatus;

    public TableReservationResponse(@NotNull LocalDate date,
                                    @NotNull LocalTime hour,
                                    @NotNull Integer countPeople,
                                    @NotNull Double totalPrice,
                                    @NotNull TableRestaurant tableRestaurant,
                                    @NotNull User user, Boolean paymentStatus) {

                                    this.date = date;
                                    this.hour = hour;
                                    this.countPeople = countPeople;
                                    this.totalPrice = totalPrice;
                                    this.tableRestaurant = tableRestaurant;
                                    this.user = user;
                                    this.paymentStatus = paymentStatus;
    }
}
