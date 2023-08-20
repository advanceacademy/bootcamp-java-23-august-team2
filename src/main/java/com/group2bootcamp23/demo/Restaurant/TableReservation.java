package com.group2bootcamp23.demo.Restaurant;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity(name = "Reservations")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TableReservation {

    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "date")
    @NotNull
    private LocalDate date;

    @Column(name = "hour")
    @NotNull
    private LocalTime hour;

    @Column(name = "count_people")
    @Min(value = 2)
    @Max(value = 11)
    private int countPeople;

    @Column(name = "total_price")
    @Min(value = 20)
    private double totalPrice;

    @Column(name = "table_number")
    @Min(value = 1)
    private int tableNumber;

    @Column(name = "user_id")
    @NotNull
    private Long userId;

    @Column(name = "payment_status")
    @NotNull(message = "Please select a payment status")
    private PaymentStatus paymentStatus;

}

