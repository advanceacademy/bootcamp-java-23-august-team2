package com.aacademy.moonlight.dto.restaurant;

import com.aacademy.moonlight.entity.restaurant.TableRestaurant;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@Builder

public class TableReservationResponse {
    private Long id;

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
    private String userName;

    private Boolean paymentStatus;

    public TableReservationResponse(Long id,
                                    @NotNull LocalDate date,
                                    @NotNull LocalTime hour,
                                    @NotNull Integer countPeople,
                                    @NotNull Double totalPrice,
                                    @NotNull TableRestaurant tableRestaurant,
                                    @NotNull String userName, Boolean paymentStatus) {

                                    this.id = id;
                                    this.date = date;
                                    this.hour = hour;
                                    this.countPeople = countPeople;
                                    this.totalPrice = totalPrice;
                                    this.tableRestaurant = tableRestaurant;
                                    this.userName = userName;
                                    this.paymentStatus = paymentStatus;
    }
}
