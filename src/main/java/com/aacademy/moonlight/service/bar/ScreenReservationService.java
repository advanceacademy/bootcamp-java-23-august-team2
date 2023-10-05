package com.aacademy.moonlight.service.bar;

import com.aacademy.moonlight.dto.bar.ScreenReservationRequest;
import com.aacademy.moonlight.dto.bar.ScreenReservationUpdateRequest;
import com.aacademy.moonlight.entity.bar.ScreenEvent;
import com.aacademy.moonlight.entity.bar.ScreenReservation;
import com.aacademy.moonlight.entity.user.User;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public interface ScreenReservationService {
    ScreenReservation addScreenReservation(ScreenReservationRequest request);

    ScreenReservation findScreenReservationById(Long id);

    List <ScreenReservation> findScreenReservationByDate(LocalDate date);

    List <ScreenReservation> findScreenReservationByUser(User user);

    List <ScreenReservation> findScreenReservationByScreen(ScreenEvent screenEvent);

    ScreenReservation updateScreenReservation(ScreenReservationUpdateRequest request, Long id);

    void deleteScreenReservationById(Long id);

}
