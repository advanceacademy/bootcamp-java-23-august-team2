package com.aacademy.moonlight.dto.bar;

import com.aacademy.moonlight.entity.bar.BarZone;
import com.aacademy.moonlight.entity.bar.ScreenSeat;
import lombok.*;

import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class BarScreenUpdateRequest {
    private BarZone barZone;

    private Set<ScreenSeat> seats;
}
