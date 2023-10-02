package com.aacademy.moonlight.dto.bar;

import com.aacademy.moonlight.entity.bar.ScreenEvent;
import com.aacademy.moonlight.entity.bar.ScreenSeat;
import com.aacademy.moonlight.entity.user.User;
import lombok.*;

import java.time.LocalDate;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ScreenReservationResponse {
    private LocalDate date;

    private ScreenEvent screenEvent;

    private Double totalPrice;

    private Set<ScreenSeat> screenSeat;

    private User user;

    private boolean paymentStatus;
}
