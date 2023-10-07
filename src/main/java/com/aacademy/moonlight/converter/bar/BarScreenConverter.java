package com.aacademy.moonlight.converter.bar;

import com.aacademy.moonlight.dto.bar.BarScreenRequest;
import com.aacademy.moonlight.entity.bar.BarScreen;
import org.springframework.stereotype.Component;

@Component
public class BarScreenConverter {
    public BarScreen toBarScreen(BarScreenRequest request){
        return BarScreen.builder()
                .barZone(request.getBarZone())
                .seats(request.getSeats())
                .build();
    }
}
