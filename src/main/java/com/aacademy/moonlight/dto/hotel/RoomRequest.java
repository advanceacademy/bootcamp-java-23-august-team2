package com.aacademy.moonlight.dto.hotel;

import com.aacademy.moonlight.entity.hotel.RoomType;
import com.aacademy.moonlight.entity.hotel.RoomView;
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

    private Integer roomNumber;

    private Double price;

    private RoomType type;

    private RoomView view;

    private List<Long> facilityIds;

}
