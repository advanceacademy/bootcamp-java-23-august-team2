package com.aacademy.moonlight.dto.bar;

import com.aacademy.moonlight.entity.bar.BarZone;
import com.aacademy.moonlight.entity.bar.ScreenSeat;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BarScreenRequest {
    @NotEmpty(message = "Please enter bar zone")
    private BarZone barZone;

    private Set<ScreenSeat> seats;
}
