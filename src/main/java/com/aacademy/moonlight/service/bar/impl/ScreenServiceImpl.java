package com.aacademy.moonlight.service.bar.impl;

import com.aacademy.moonlight.converter.bar.BarScreenConverter;
import com.aacademy.moonlight.dto.bar.BarScreenRequest;
import com.aacademy.moonlight.dto.bar.BarScreenUpdateRequest;
import com.aacademy.moonlight.entity.bar.BarScreen;
import com.aacademy.moonlight.repository.bar.BarScreenRepository;
import com.aacademy.moonlight.service.bar.ScreenService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Consumer;

@Service
public class ScreenServiceImpl implements ScreenService {
    private final BarScreenRepository repository;
    private final BarScreenConverter converter;

    public ScreenServiceImpl(BarScreenRepository repository, BarScreenConverter converter) {
        this.repository = repository;
        this.converter = converter;
    }

    @Override
    public BarScreen saveScreen(@Valid BarScreenRequest request) {
        BarScreen barScreen = converter.toBarScreen(request);
        return repository.save(barScreen);
    }


    @Override
    public BarScreen getScreenById(Long id) throws ChangeSetPersister.NotFoundException {
        return repository.findById(id).orElseThrow( () -> new ChangeSetPersister.NotFoundException());
    }

    @Override
    public List<BarScreen> getAllScreens() {
        return repository.findAll();
    }

    @Override
    public BarScreen updateBarScreenById(@Valid BarScreenUpdateRequest request, Long id) {
        BarScreen barScreen = repository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Bar screen with id: " + id + " is not found"));

        updateFieldIfNotNull(request.getBarZone(), barScreen::setBarZone);
        updateFieldIfNotNull(request.getSeats(), barScreen::setSeats);

        return repository.save(barScreen);
    }

    private <T> void updateFieldIfNotNull(T newValue, Consumer<T> fieldUpdater) {
        if (newValue != null) {
            fieldUpdater.accept(newValue);
        }
    }

    @Override
    public void deleteScreenById(Long id) {
        repository.deleteById(id);
    }
}
