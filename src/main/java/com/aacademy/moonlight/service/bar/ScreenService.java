package com.aacademy.moonlight.service.bar;

import com.aacademy.moonlight.dto.bar.BarScreenRequest;
import com.aacademy.moonlight.entity.bar.BarScreen;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
@Service
public interface ScreenService {

    BarScreen saveScreen(BarScreenRequest request);

    BarScreen getScreenById(Long id) throws ChangeSetPersister.NotFoundException;

    Iterable<BarScreen> getAllScreens();

    BarScreen updateBarScreenById(BarScreenRequest request, Long id);

    void deleteScreenById(Long id);
}
