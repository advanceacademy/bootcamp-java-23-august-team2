package com.aacademy.moonlight.service.bar;

import com.aacademy.moonlight.dto.bar.ScreenEventRequest;
import com.aacademy.moonlight.entity.bar.ScreenEvent;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public interface ScreenEventService {
    ScreenEvent addScreenEvent(ScreenEventRequest request);

    ScreenEvent findScreenEventById(Long id);

    List<ScreenEvent> findAllEvents();

    List<ScreenEvent> findEventByDate(LocalDate date);

    ScreenEvent updateScreenEvent(ScreenEventRequest request, Long id);

    void deleteEvent(Long id);
}
