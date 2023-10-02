package com.aacademy.moonlight.dto.bar;

import com.aacademy.moonlight.entity.bar.BarScreen;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ScreenSeatRequest {
    @NotEmpty(message = "Position is required")
    private String position;

    private BarScreen barScreen;
}
