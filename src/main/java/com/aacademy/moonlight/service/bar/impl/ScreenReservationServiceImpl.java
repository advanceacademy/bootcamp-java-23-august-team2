package com.aacademy.moonlight.service.bar.impl;

import com.aacademy.moonlight.converter.bar.ScreenReservationConverter;
import com.aacademy.moonlight.dto.bar.ScreenReservationRequest;
import com.aacademy.moonlight.dto.bar.ScreenReservationUpdateRequest;
import com.aacademy.moonlight.entity.bar.ScreenEvent;
import com.aacademy.moonlight.entity.bar.ScreenReservation;
import com.aacademy.moonlight.entity.user.User;
import com.aacademy.moonlight.repository.bar.ScreenReservationRepository;
import com.aacademy.moonlight.service.bar.ScreenReservationService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

@Service
public class ScreenReservationServiceImpl implements ScreenReservationService {

    private final ScreenReservationRepository repository;
    private final ScreenReservationConverter converter;

    public ScreenReservationServiceImpl(ScreenReservationRepository repository, ScreenReservationConverter converter) {
        this.repository = repository;
        this.converter = converter;
    }

    @Override
    public ScreenReservation addScreenReservation(@Valid ScreenReservationRequest request) {
        ScreenReservation screenReservation = converter.toScreenReservation(request);
        return repository.save(screenReservation);
    }

    @Override
    public ScreenReservation findScreenReservationById(Long id) {
        ScreenReservation screenReservation = repository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Reservation with id: " + id + " is not found")
        );
        return screenReservation;
    }

    @Override
    public List <ScreenReservation> findScreenReservationByDate(LocalDate date) {
        List<ScreenReservation> allReservationList = repository.findAll();
        List <ScreenReservation> filterdList = new ArrayList<>();
        for(ScreenReservation reservation:allReservationList){
            if(reservation.getDate().equals(date)){
                filterdList.add(reservation);
            }
        }
        return filterdList;
    }

    @Override
    public List<ScreenReservation> findScreenReservationByUser(User user) {
        List <ScreenReservation> allReservations = repository.findAll();
        List<ScreenReservation> filteredReservation = new ArrayList<>();
        for(ScreenReservation reservation: allReservations){
            if(reservation.getUser().equals(user)){
                filteredReservation.add(reservation);
            }
        }
        return filteredReservation;
    }

    @Override
    public List<ScreenReservation> findScreenReservationByScreen(ScreenEvent screenEvent) {
        List <ScreenReservation> allReservations = repository.findAll();
        List<ScreenReservation> filteredReservation = new ArrayList<>();
        for(ScreenReservation reservation: allReservations){
            if(reservation.getScreenEvent().equals(screenEvent)){
                filteredReservation.add(reservation);
            }
        }
        return filteredReservation;
    }

    @Override
    public ScreenReservation updateScreenReservation(@Valid ScreenReservationUpdateRequest request, Long id) {
        ScreenReservation reservation = repository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Reservation with id: " + id + " is not found")
        );
        updateFieldIfNotNull(request.getScreenEvent(), reservation::setScreenEvent);
        updateFieldIfNotNull(request.getScreenSeat(), reservation::setScreenSeat);
        updateFieldIfNotNull(request.getUser(), reservation::setUser);
        updateFieldIfNotNull(request.getDate(), reservation::setDate);
        updateFieldIfNotNull(request.isPaymentStatus(), reservation::setPaymentStatus);
        if(request.getScreenSeat() != null || request.getDate() != null)
        {
            reservation.setTotalPrice(request.getScreenSeat().size() * 5.0);
        }
        return repository.save(reservation);
    }

    @Override
    public void deleteScreenReservationById(Long id) {
        repository.deleteById(id);
    }

    private <T> void updateFieldIfNotNull(T newValue, Consumer<T> fieldUpdater) {
        if (newValue != null) {
            fieldUpdater.accept(newValue);
        }
    }
}
