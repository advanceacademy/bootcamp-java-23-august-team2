package com.aacademy.moonlight.service.car;

import com.aacademy.moonlight.dto.car.FileResourcesForCarRequest;
import com.aacademy.moonlight.entity.car.FileResourcesForCar;

import java.util.List;

public interface FileResourcesForCarService {
    FileResourcesForCar saveFileResourcesForCar(FileResourcesForCarRequest request);

    FileResourcesForCar getFileResourcesById(Long id);

    FileResourcesForCar updateFileResources(FileResourcesForCarRequest request, Long id);

    void deleteFileResourcesById(Long id);

    FileResourcesForCar findFileByName(String name);
    String determineContentType(String imageName);
}
