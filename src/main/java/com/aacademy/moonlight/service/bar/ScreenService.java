package com.aacademy.moonlight.service.bar;

import com.aacademy.moonlight.dto.bar.BarScreenRequest;
import com.aacademy.moonlight.dto.bar.BarScreenUpdateRequest;
import com.aacademy.moonlight.entity.bar.BarScreen;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ScreenService {

    BarScreen saveScreen(BarScreenRequest request);


    BarScreen getScreenById(Long id) throws ChangeSetPersister.NotFoundException;

    List<BarScreen> getAllScreens();

    BarScreen updateBarScreenById(BarScreenUpdateRequest request, Long id);

    void deleteScreenById(Long id);
}
