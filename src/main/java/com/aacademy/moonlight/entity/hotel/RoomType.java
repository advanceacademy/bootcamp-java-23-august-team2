package com.aacademy.moonlight.entity.hotel;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum RoomType {

    STANDARD(2),
    STUDIO(3),
    APARTMENT(4);

    private final int roomCapacity;

}

