package com.aacademy.moonlight.converter.bar;

import com.aacademy.moonlight.dto.bar.ScreenEventRequest;
import com.aacademy.moonlight.entity.bar.ScreenEvent;
import org.springframework.stereotype.Component;

@Component
public class ScreenEventConverter {
    public ScreenEvent toScreenEvent(ScreenEventRequest request){
        return ScreenEvent.builder()
                .barScreen(request.getBarScreen())
                .event(request.getEvent())
                .date(request.getDate())
                .build();
    }
}
