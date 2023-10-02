package com.aacademy.moonlight.converter.bar;

import com.aacademy.moonlight.dto.bar.ScreenReservationRequest;
import com.aacademy.moonlight.entity.bar.ScreenReservation;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class ScreenReservationConverter {
    public ScreenReservation toScreenReservation(ScreenReservationRequest request){
        return ScreenReservation.builder()
                .date(request.getDate())
                .user(request.getUser())
                .screenEvent(request.getScreenEvent())
                .screenSeat(request.getScreenSeat())
                .totalPrice(calculateTotalPrice(request.getScreenSeat().size()))
                .paymentStatus(false)
                .build();
    }
    public Double calculateTotalPrice(int numberOfSeats){
        return numberOfSeats * 5.0;
    }
}
