package com.aacademy.moonlight.service.bar.impl;

import com.aacademy.moonlight.converter.bar.ScreenEventConverter;
import com.aacademy.moonlight.dto.bar.ScreenEventRequest;
import com.aacademy.moonlight.dto.bar.ScreenEventResponse;
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
        List<ScreenEventResponse> allEventsOnThisDate = findEventByDate(request.getDate());

        ScreenEvent newEvent = converter.toScreenEvent(request);
        if(allEventsOnThisDate.size() < 3){
            for(ScreenEventResponse event:allEventsOnThisDate){
                if(event.getBarZone().equals(request.getBarScreen().getBarZone().name())){
                    throw new CredentialException(
                            "Event for " + event.getBarZone() + " and date "
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
    public List<ScreenEventResponse> findEventByDate(LocalDate date) {
        List<ScreenEvent> allEvents = repository.findAll();
        List<ScreenEventResponse> filteredEvents = new ArrayList<>();

        //Adding filtered events, by date, to list
        for (ScreenEvent event : allEvents){
            if (event.getDate().equals(date)){
                filteredEvents.add(converter.toScreenEventResponse(event));
            }
        }
        return filteredEvents;
    }

    @Override
    public List<ScreenEventResponse> findEventByName(String eventName) {
        List<ScreenEvent> allEvents = repository.findAll();
        List<ScreenEventResponse> eventsByName = new ArrayList<>();
        for(ScreenEvent event: allEvents){
            if (event.getEvent().equals(eventName)){
                eventsByName.add(converter.toScreenEventResponse(event));
            }
        }
        return eventsByName;
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
