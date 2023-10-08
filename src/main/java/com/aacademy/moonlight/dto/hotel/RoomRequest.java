package com.aacademy.moonlight.dto.hotel;

import com.aacademy.moonlight.entity.hotel.RoomReservation;
import com.aacademy.moonlight.entity.hotel.RoomType;
import com.aacademy.moonlight.entity.hotel.RoomView;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;


import java.util.List;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class RoomRequest {

    @NotNull
    @Min(value = 1)
    private Long roomNumber;

    @NotNull
    @Min(value = 100)
    private Double price;

    @NotNull
    private Integer area;

    @NotNull
    private RoomType type;

    @NotNull
    private RoomView view;

    @NotNull
    private List<Long> facilityIds;

    private Set<RoomReservation> reservationSet;

}
