package com.aacademy.moonlight.service.car.impl;

import com.aacademy.moonlight.converter.car.FileResourcesForCarConverter;
import com.aacademy.moonlight.dto.car.FileResourcesForCarRequest;
import com.aacademy.moonlight.entity.car.FileResourcesForCar;
import com.aacademy.moonlight.repository.car.FileResourcesForCarRepository;
import com.aacademy.moonlight.service.car.FileResourcesForCarService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class FileResourcesForCarServiceImpl implements FileResourcesForCarService {
    private final FileResourcesForCarRepository repository;
    private final FileResourcesForCarConverter converter;

    public FileResourcesForCarServiceImpl(FileResourcesForCarRepository repository, FileResourcesForCarConverter converter) {
        this.repository = repository;
        this.converter = converter;
    }

    @Override
    public FileResourcesForCar saveFileResourcesForCar(FileResourcesForCarRequest request) {
        FileResourcesForCar fileResourcesForCar = converter.toFileResources(request);
        return repository.save(fileResourcesForCar);
    }

    @Override
    public FileResourcesForCar getFileResourcesById(Long id) {
        return repository.findById(id).orElseThrow(
                () -> new RuntimeException("Car category with this id not found.")
        );
    }

    @Override
    public FileResourcesForCar updateFileResources(FileResourcesForCarRequest request, Long id) {
        FileResourcesForCar fileResourcesForCar = repository.findById(id).orElseThrow(
                () -> new RuntimeException("Car category not found.")
        );
        fileResourcesForCar.setImageName(request.getImageName());
        fileResourcesForCar.setPhoto(request.getPhoto());
        fileResourcesForCar.setCar(request.getCar());
        return repository.save(fileResourcesForCar);
    }

    @Override
    public void deleteFileResourcesById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public FileResourcesForCar findFileByName(String name) {
        List<FileResourcesForCar> files = repository.findAll();
        FileResourcesForCar currentFile = null;

        for (FileResourcesForCar file : files){
            if (Objects.equals(file.getImageName(), name)){
                currentFile = file;
            }
        }
        return currentFile;
    }
    @Override
    public String determineContentType(String imageName) {
        if (imageName != null && imageName.toLowerCase().endsWith(".png")) {
            return MediaType.IMAGE_PNG_VALUE;
        } else {
            return MediaType.IMAGE_JPEG_VALUE; // Default to JPEG
        }
    }
}
