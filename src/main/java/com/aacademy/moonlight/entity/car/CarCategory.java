package com.aacademy.moonlight.entity.car;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Table(name = "car_categories")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class CarCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    @NotNull()
    private CarType type;

    @Column(name = "seats")
    @NotNull
    @Min(value = 2)
    private Integer seats;
    //Here we can add some connection with carType to fill the value automatic

    @Column(name = "price_per_day")
    @NotNull(message = "Car should have price")
    @Min(value = 600)
    private Double price;
    //Here we can add some connection with carType to fill the value automatic
}
