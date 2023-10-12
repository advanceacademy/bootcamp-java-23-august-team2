package com.aacademy.moonlight.service.bar.impl;

import com.aacademy.moonlight.converter.bar.ScreenEventConverter;
import com.aacademy.moonlight.dto.bar.ScreenEventRequest;
import com.aacademy.moonlight.entity.bar.ScreenEvent;
import com.aacademy.moonlight.repository.bar.ScreenEventRepository;
import com.aacademy.moonlight.service.bar.ScreenEventService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import javax.security.auth.login.CredentialException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

@Service
public class ScreenEventServiceImpl implements ScreenEventService {
    private final ScreenEventRepository repository;
    private final ScreenEventConverter converter;

    public ScreenEventServiceImpl(ScreenEventRepository repository, ScreenEventConverter converter) {
        this.repository = repository;
        this.converter = converter;
    }

    @Override
    public ScreenEvent addScreenEvent(@Valid ScreenEventRequest request) throws CredentialException {
        //Find all already existing events on this date
        List<ScreenEvent> allEventsOnThisDate = findEventByDate(request.getDate());
        ScreenEvent newEvent = converter.toScreenEvent(request);
        if(allEventsOnThisDate.size() < 3){
            for(ScreenEvent event:allEventsOnThisDate){
                if(event.getBarScreen().getId().equals(request.getBarScreen().getId())){
                    throw new CredentialException(
                            "Event for " + event.getBarScreen().getBarZone() + " and date "
                                    + request.getDate() + " already exists");
                }
                else {

                    return repository.save(newEvent);
                }
            }
        }
        else {
            throw new CredentialException("Events must be only three per day");
        }
        return repository.save(newEvent);
    }

    @Override
    public ScreenEvent findScreenEventById(Long id) {
        return repository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Event with id: " + id + " is not found."));
    }

    @Override
    public List<ScreenEvent> findAllEvents() {
        List<ScreenEvent> events = new ArrayList<>();
        for(ScreenEvent event: repository.findAll()){
            events.add(event);
        }
        return events;
    }

    @Override
    public List<ScreenEvent> findEventByDate(LocalDate date) {
        List<ScreenEvent> allEvents = repository.findAll();
        List<ScreenEvent> filteredEvents = new ArrayList<>();

        //Adding filtered events, by date, to list
        for (ScreenEvent event : allEvents){
            if (event.getDate().equals(date)){
                filteredEvents.add(event);
            }
        }
        return filteredEvents;
    }

    @Override
    public ScreenEvent updateScreenEvent(@Valid ScreenEventRequest request, Long id) {
        ScreenEvent event = repository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Event with id: " + id + " is not found"));
        updateFieldIfNotNull(request.getBarScreen(), event::setBarScreen);
        updateFieldIfNotNull(request.getEvent(), event::setEvent);
        updateFieldIfNotNull(request.getDate(), event::setDate);

        return repository.save(event);
    }

    @Override
    public void deleteEvent(Long id) {
        repository.deleteById(id);
    }

    private <T> void updateFieldIfNotNull(T newValue, Consumer<T> fieldUpdater) {
        if (newValue != null) {
            fieldUpdater.accept(newValue);
        }
    }
}
