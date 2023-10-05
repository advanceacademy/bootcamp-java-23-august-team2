package com.aacademy.moonlight.dto.bar;

import com.aacademy.moonlight.entity.bar.ScreenEvent;
import com.aacademy.moonlight.entity.bar.ScreenSeat;
import com.aacademy.moonlight.entity.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ScreenReservationUpdateRequest {

    private LocalDate date;

    private ScreenEvent screenEvent;

    private Set<ScreenSeat> screenSeat;

    private User user;

    private double totalPrice;

    private boolean paymentStatus;
}
