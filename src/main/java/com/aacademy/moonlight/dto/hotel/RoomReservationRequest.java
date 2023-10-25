package com.aacademy.moonlight.dto.hotel;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RoomReservationRequest {

    @NotNull
    @Future()
    private LocalDate startDate;

    @NotNull
    @Future()
    private LocalDate endDate;

    @NotNull
    @Min(value = 1)
    private  Integer adults;

    @NotNull
    @Min(value = 0)
    private Integer children;

    @NotNull
    private Long roomId;

    @NotNull
    private Boolean paymentStatus;
}
