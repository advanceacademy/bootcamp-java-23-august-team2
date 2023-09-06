package com.aacademy.moonlight.dto.hotel;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RoomFacilityRequest {

    @NotEmpty(message = "Facility should have name ")
    private String facility;
}
