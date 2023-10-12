package com.aacademy.moonlight.runner;

import com.aacademy.moonlight.entity.bar.BarScreen;
import com.aacademy.moonlight.entity.bar.BarZone;
import com.aacademy.moonlight.entity.bar.ScreenSeat;
import com.aacademy.moonlight.repository.bar.BarScreenRepository;
import com.aacademy.moonlight.repository.bar.ScreenSeatRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AddScreenCommandRunner implements CommandLineRunner {
    private final BarScreenRepository screenRepository;

    private final ScreenSeatRepository seatRepository;

    public AddScreenCommandRunner(BarScreenRepository screenRepository,
                                  ScreenSeatRepository seatRepository) {
        this.screenRepository = screenRepository;
        this.seatRepository = seatRepository;
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
        List<ScreenSeat> seatList = seatRepository.findAll();
        List<ScreenSeat>seatsOnThisScreen = new ArrayList<>();
        for(ScreenSeat seat: seatList){
            if (seat.getBarScreen().getId().equals(screen.getId())){
                seatsOnThisScreen.add(seat);
            }
            else {
                break;
            }
        }
        boolean isExisting = true;
        if (Integer.parseInt(position) < 22 && Integer.parseInt(position) > 1) {
            for (ScreenSeat seat : seatsOnThisScreen) {

                if (position.equals(seat.getPosition())) {
                    isExisting = true;
                    break;
                } else {
                    isExisting = false;
                    return seatRepository.save(seat);
                }
            }
        }
        return null;
    }
}
