package com.group2bootcamp23.demo.Restaurant;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "Tables")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TableRestaurant {


    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "table_number")
    @Min(value = 1)
    @Max(value = 16)
    private int tableNumber;

    @Column(name = "table_zone")
    @NotNull(message = "Table should have a zone")
    private TableZone tableZone;

    @Column(name = "is_smoking")
    private boolean is_Smoking;

    @Column(name = "seats")
    @Min(value = 1)
    @Max(value = 11)
    private int seats;

    @Column(name = "price")
    @Min(value = 10)
    private double price;


}
