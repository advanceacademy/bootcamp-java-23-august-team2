package com.aacademy.moonlight.runner;

import com.aacademy.moonlight.entity.bar.BarScreen;
import com.aacademy.moonlight.entity.bar.BarZone;
import com.aacademy.moonlight.entity.bar.ScreenSeat;
import com.aacademy.moonlight.repository.bar.BarScreenRepository;
import com.aacademy.moonlight.repository.bar.ScreenSeatRepository;
import com.aacademy.moonlight.service.bar.ScreenSeatService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AddScreenCommandRunner implements CommandLineRunner {
    private final BarScreenRepository screenRepository;

    private final ScreenSeatRepository seatRepository;

    private final ScreenSeatService seatService;

    public AddScreenCommandRunner(BarScreenRepository screenRepository,
                                  ScreenSeatRepository seatRepository,
                                  ScreenSeatService seatService) {
        this.screenRepository = screenRepository;
        this.seatRepository = seatRepository;
        this.seatService = seatService;
    }

    @Override
    public void run(String... args) throws Exception {
        //creating bar screens without seats
        createBarScreen(BarZone.SCREEN_ONE);
        createBarScreen(BarZone.SCREEN_TWO);
        createBarScreen(BarZone.SCREEN_THREE);

        //creating screen seats
        for(int i = 1; i<=21; i++){
            createScreenSeat(String.valueOf(i), screenRepository.findById(1L).orElseThrow());
        }
        for(int i = 1; i<=21; i++){
            createScreenSeat(String.valueOf(i), screenRepository.findById(2L).orElseThrow());
        }
        for(int i = 1; i<=21; i++){
            createScreenSeat(String.valueOf(i), screenRepository.findById(5L).orElseThrow());
        }
    }
    private void createBarScreen(BarZone barZone){
        List<BarScreen> existingBarScreen = screenRepository.findAll();

        boolean isExisting = true;

        BarScreen screen = BarScreen.builder()
                .barZone(barZone)
                .build();
        for(BarScreen barScreen:existingBarScreen){
            if(screen.getBarZone().equals(barScreen.getBarZone())){
                isExisting = true;
                break;
            }
            else{
                isExisting = false;
            }
        }
        if(!isExisting){
            screenRepository.save(screen);
            existingBarScreen.add(screen);
        }
    }

    private ScreenSeat createScreenSeat(String position, BarScreen screen) {
        List<ScreenSeat>seatsOnThisScreen = seatService.findScreenSeatsByScreen(screen);
        boolean isExisting = true;
        if(seatsOnThisScreen.size()==0){
            isExisting = false;
        }

        if (Integer.parseInt(position) < 22 && Integer.parseInt(position) > 0) {
            for (ScreenSeat seat : seatsOnThisScreen) {
                if (position.equals(seat.getPosition())) {
                    isExisting = true;
                    break;
                } else {
                    isExisting = false;
                }
            }
        }
        if(!isExisting){
            ScreenSeat seat = ScreenSeat.builder()
                    .barScreen(screen)
                    .position(position)
                    .build();
            return seatRepository.save(seat);
        }
        return null;
    }
}
