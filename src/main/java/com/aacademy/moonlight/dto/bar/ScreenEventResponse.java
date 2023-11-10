package com.aacademy.moonlight.dto.bar;

import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ScreenEventResponse {
    private String event;

    private LocalDate date;

    private String barZone;
}
