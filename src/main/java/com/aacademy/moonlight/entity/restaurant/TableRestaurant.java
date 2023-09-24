package com.aacademy.moonlight.entity.restaurant;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Table(name = "tables")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TableRestaurant {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "table_number")
    @Min(value = 1)
    @Max(value = 16)
    @NotNull(message = "Please select a table number")
    private Integer tableNumber;

    @Column(name = "table_zone")
    @Enumerated(EnumType.STRING)
    @NotNull(message = "Please select a table zone")
    private TableZone tableZone;

    @Column(name = "is_smoking")
    @NotNull(message = "Table should have a smoking status")
    private Boolean is_Smoking;

    @Column(name = "seats")
    @Min(value = 1)
    @Max(value = 11)
    @NotNull(message = "Please select the number of seats")
    private Integer seats;

    @Column(name = "price")
    @Min(value = 10)
    @NotNull(message = "Missing price")
    private Double price;
}
