package com.aacademy.moonlight.service.bar.impl;

import com.aacademy.moonlight.service.bar.ScreenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class ScreenService {
    private final ScreenRepository screenRepository;

    @Autowired
    public ScreenService(ScreenRepository screenRepository) {
        this.screenRepository = screenRepository;
    }

    public Screen saveScreen(Screen screen) {
        return screenRepository.save(screen);
    }

    public Screen getScreenById(Long id) {
        return screenRepository.findById(id).orElse(null);
    }

    public Iterable<Screen> getAllScreens() {
        return screenRepository.findAll();
    }

    public void deleteScreenById(Long id) {
        screenRepository.deleteById(id);
    }
}
