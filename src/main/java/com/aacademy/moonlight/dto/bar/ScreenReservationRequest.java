package com.aacademy.moonlight.dto.bar;

import com.aacademy.moonlight.entity.bar.ScreenEvent;
import com.aacademy.moonlight.entity.bar.ScreenSeat;
import com.aacademy.moonlight.entity.user.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
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
public class ScreenReservationRequest {
    @NotNull(message = "Please pick a date")
    @Future
    private LocalDate date;

    private ScreenEvent screenEvent;

    private Set<ScreenSeat> screenSeat;

    private User user;
}
