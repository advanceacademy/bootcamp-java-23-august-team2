package com.aacademy.moonlight.entity.restaurant;


import com.aacademy.moonlight.entity.user.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "table_reservations")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TableReservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "date")
    @FutureOrPresent(message = "Date must be in the future")
    @NotNull
    private LocalDate date;

    @Column(name = "hour")
    @NotNull
    private LocalTime hour;

    @Column(name = "count_people")
    @Min(value = 1)
    @Max(value = 11)
    @NotNull(message = "Please select the number of people")
    private Integer countPeople;

    @Column(name = "total_price")
    @Min(value = 10)
    @NotNull(message = "Missing price")
    private Double totalPrice;

    @JoinColumn(name = "table_restaurant")
    @ManyToOne
    private TableRestaurant tableRestaurant;

    @JoinColumn(name = "user")
    @ManyToOne
    @NotNull
    private User User;

    @Column(name = "payment_status")
    private boolean paymentStatus;

}

