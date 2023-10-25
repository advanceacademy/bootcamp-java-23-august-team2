package com.aacademy.moonlight.dto.hotel;

import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class RoomReservationResponse {
    private Long roomId;
    private LocalDate startDate;
    private LocalDate endDate;
    private String userName;
    private Double totalPrice;

}
