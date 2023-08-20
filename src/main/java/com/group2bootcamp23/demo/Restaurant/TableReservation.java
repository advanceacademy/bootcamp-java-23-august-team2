package com.group2bootcamp23.demo.Restaurant;


import java.time.LocalDate;
import java.time.LocalTime;

public class TableReservation {
    private Long id;
    private LocalDate date;
    private LocalTime hour;
    private int countPeople;
    private double totalPrice;
    private int tableNumber;
    private Long userId;
    private PaymentStatus paymentStatus;

}

