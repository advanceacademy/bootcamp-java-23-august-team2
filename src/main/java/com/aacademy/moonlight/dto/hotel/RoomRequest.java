package com.aacademy.moonlight.dto.hotel;

import com.aacademy.moonlight.entity.hotel.RoomType;
import com.aacademy.moonlight.entity.hotel.RoomView;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RoomRequest {

    @NotNull
    @Min(value = 1)
    private Integer roomNumber;

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

}
