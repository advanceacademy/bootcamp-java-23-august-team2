package com.aacademy.moonlight.dto.restaurant;

import com.aacademy.moonlight.entity.restaurant.TableZone;


import lombok.*;
@Getter
@Setter
@NoArgsConstructor
@Builder

public class TableRestaurantResponse {

    private Long id;

    private Integer tableNumber;

    private TableZone tableZone;

    private boolean is_Smoking;

    private Integer seats;

    private Double price;



    public TableRestaurantResponse(Long id, Integer tableNumber, TableZone tableZone, boolean is_Smoking, Integer seats, Double price) {
        this.id = id;
        this.tableNumber = tableNumber;
        this.tableZone = tableZone;
        this.is_Smoking = is_Smoking;
        this.seats = seats;
        this.price = price;

    }
}
