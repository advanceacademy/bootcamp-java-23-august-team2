package com.aacademy.moonlight.service.bar.impl;

import com.aacademy.moonlight.dto.bar.ScreenSeatRequest;
import com.aacademy.moonlight.entity.bar.BarScreen;
import com.aacademy.moonlight.entity.bar.ScreenSeat;
import com.aacademy.moonlight.repository.bar.ScreenSeatRepository;
import com.aacademy.moonlight.service.bar.ScreenSeatService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

@Service
public class ScreenSeatServiceImpl implements ScreenSeatService {

    private  final ScreenSeatRepository repository;

    public ScreenSeatServiceImpl(ScreenSeatRepository repository) {
        this.repository = repository;
    }

    @Override
    public ScreenSeat addScreenSeat(@Valid ScreenSeatRequest request) {
        //Create new screen seat
        ScreenSeat screenSeat = new ScreenSeat();
        screenSeat.setBarScreen(request.getBarScreen());
        screenSeat.setPosition(request.getPosition());

        return repository.save(screenSeat);
    }

    @Override
    public ScreenSeat findSeatById(Long id) {
        ScreenSeat screenSeat = repository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Seat with id: " + id + " does not found"));
        return screenSeat;
    }

    @Override
    public List<ScreenSeat> findScreenSeatsByScreen(BarScreen screen) {
        List<ScreenSeat> allScreenSeats = repository.findAll();
        List<ScreenSeat> filteredScreenSeats = new ArrayList<>();

        for(ScreenSeat seat : allScreenSeats){
            if(seat.getBarScreen().getId() == screen.getId()){
                filteredScreenSeats.add(seat);
            }
        }

        return filteredScreenSeats;
    }

    @Override
    public ScreenSeat updateScreenSeat(ScreenSeatRequest request, Long id) {
        //Get the seat, that will be updated
        ScreenSeat screenSeat = repository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Seat with id: " + id + " does not found")
        );
        //Set new properties to the chosen seat
        updateFieldIfNotNull(request.getBarScreen(), screenSeat::setBarScreen);
        updateFieldIfNotNull(request.getPosition(), screenSeat::setPosition);

        return repository.save(screenSeat);
    }

    @Override
    public void deleteScreenSeat(Long id) {
        repository.deleteById(id);
    }

    private <T> void updateFieldIfNotNull(T newValue, Consumer<T> fieldUpdater) {
        if (newValue != null) {
            fieldUpdater.accept(newValue);
        }
    }
}
