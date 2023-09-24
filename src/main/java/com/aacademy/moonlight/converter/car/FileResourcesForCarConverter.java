package com.aacademy.moonlight.converter.car;

import com.aacademy.moonlight.dto.car.FileResourcesForCarRequest;
import com.aacademy.moonlight.entity.car.FileResourcesForCar;
import org.springframework.stereotype.Component;

@Component
public class FileResourcesForCarConverter {

    public FileResourcesForCar toFileResources(FileResourcesForCarRequest request){
       return FileResourcesForCar.builder()
               .imageName(request.getImageName())
               .photo(request.getPhoto())
               .car(request.getCar())
               .build();
    }
}
