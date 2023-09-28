package com.aacademy.moonlight.controller.car;

import com.aacademy.moonlight.entity.car.Car;
import com.aacademy.moonlight.entity.car.FileResourcesForCar;
import com.aacademy.moonlight.service.car.CarService;
import com.aacademy.moonlight.service.car.FileResourcesForCarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/auth/images")
class CarImageController {
    @Autowired
    FileResourcesForCarService fileResourcesForCarService;
    @Autowired
    CarService carService;

    @GetMapping(path = "/{id}")
    public ResponseEntity<byte[]> getImageById(@PathVariable Long id) {
        FileResourcesForCar carImage = fileResourcesForCarService.getFileResourcesById(id);
        byte[] imageData = carImage.getPhoto();

        String imageName = carImage.getImageName();
        String contentType = fileResourcesForCarService.determineContentType(imageName);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType(contentType));

        return new ResponseEntity<>(imageData, headers, HttpStatus.FOUND);
    }


    @GetMapping(path = "/car-images/{id}")
    public ResponseEntity<List<ResponseEntity<byte[]>>> getImagesForCar(@PathVariable Long id) {
        Car car = carService.getCarById(id);

        List<FileResourcesForCar> carImages = car.getFileResourcesForCar();
        List<ResponseEntity<byte[]>> imageResponses = new ArrayList<>();

        for (FileResourcesForCar carImage : carImages) {
            byte[] imageData = carImage.getPhoto();
            String imageName = carImage.getImageName();
            String contentType = fileResourcesForCarService.determineContentType(imageName);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.parseMediaType(contentType));

            ResponseEntity<byte[]> imageResponse = new ResponseEntity<>(imageData, headers, HttpStatus.FOUND);
            imageResponses.add(imageResponse);
        }

        return ResponseEntity.ok(imageResponses);
    }
}
