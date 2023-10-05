package com.aacademy.moonlight.service.bar;

import com.aacademy.moonlight.dto.bar.BarScreenRequest;
import com.aacademy.moonlight.dto.bar.ScreenSeatRequest;
import com.aacademy.moonlight.entity.bar.BarScreen;
import com.aacademy.moonlight.entity.bar.ScreenSeat;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ScreenSeatService {
    ScreenSeat addScreenSeat (ScreenSeatRequest request);

    ScreenSeat findSeatById(Long id);

    List<ScreenSeat> findScreenSeatsByScreen(BarScreen screen);

    ScreenSeat updateScreenSeat(ScreenSeatRequest request, Long id);

    void deleteScreenSeat(Long id);
}
