package com.aacademy.moonlight.dto.hotel;

import java.time.LocalDate;

public class RoomReservationRequest {

    private LocalDate startDate;

    private LocalDate endDate;

    private  Integer adults;

    private int children;

    private Long roomId;

    private Long userId;

    private boolean paymentStatus;
}
