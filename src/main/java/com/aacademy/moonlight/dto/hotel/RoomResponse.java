package com.aacademy.moonlight.dto.hotel;

import com.aacademy.moonlight.entity.hotel.RoomFacility;
import com.aacademy.moonlight.entity.hotel.RoomReservation;
import com.aacademy.moonlight.entity.hotel.RoomType;
import com.aacademy.moonlight.entity.hotel.RoomView;
import jakarta.persistence.*;
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
public class RoomResponse {

    private Integer id;

    @Min(value = 1)
    @NotNull(message = "Room should have room number")
    private Integer roomNumber;

    @Min(value = 220) // Need to be modified
    @NotNull(message = "Room should have price")
    private Double price;

    @NotNull(message = "Room should have an area in square meters")
    private Integer area;

    @NotNull()
    private RoomType type;

    @NotNull()
    private RoomView view;

    private List<RoomFacility> facilities;

    private Integer roomCapacity;
   //private Set<RoomReservation> roomReservations;
}

