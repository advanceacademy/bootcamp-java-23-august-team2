package com.aacademy.moonlight.service.bar;

import com.aacademy.moonlight.dto.bar.ScreenEventRequest;
import com.aacademy.moonlight.dto.bar.ScreenEventResponse;
import com.aacademy.moonlight.entity.bar.ScreenEvent;
import org.springframework.stereotype.Service;

import javax.security.auth.login.CredentialException;
import java.time.LocalDate;
import java.util.List;

@Service
public interface ScreenEventService {
    ScreenEvent addScreenEvent(ScreenEventRequest request) throws CredentialException;

    ScreenEvent findScreenEventById(Long id);

    List<ScreenEvent> findAllEvents();

    List<ScreenEventResponse> findEventByDate(LocalDate date);
    List<ScreenEventResponse> findEventByName(String eventName);

    ScreenEvent updateScreenEvent(ScreenEventRequest request, Long id);

    void deleteEvent(Long id);
}
