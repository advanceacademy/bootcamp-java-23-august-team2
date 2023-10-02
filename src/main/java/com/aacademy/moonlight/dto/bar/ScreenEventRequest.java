package com.aacademy.moonlight.dto.bar;

import com.aacademy.moonlight.entity.bar.BarScreen;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ScreenEventRequest {
    @NotEmpty(message = "Enter event name")
    private String event;

    @NotNull(message = "Enter date")
    @Future
    private LocalDate date;

    private BarScreen barScreen;
}
