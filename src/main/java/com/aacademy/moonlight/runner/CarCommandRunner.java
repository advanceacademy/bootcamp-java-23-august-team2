package com.aacademy.moonlight.runner;

import com.aacademy.moonlight.entity.car.Car;
import com.aacademy.moonlight.entity.car.CarCategory;
import com.aacademy.moonlight.entity.car.CarType;
import com.aacademy.moonlight.entity.car.FileResourcesForCar;
import com.aacademy.moonlight.repository.car.CarCategoryRepository;
import com.aacademy.moonlight.repository.car.CarRepository;
import com.aacademy.moonlight.repository.car.FileResourcesForCarRepository;
import com.aacademy.moonlight.service.car.CarCategoryService;
import com.aacademy.moonlight.service.car.CarService;
import com.aacademy.moonlight.service.car.FileResourcesForCarService;
import com.aacademy.moonlight.utils.ImageFileUtil;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class CarCommandRunner implements CommandLineRunner {
    private final FileResourcesForCarRepository fileResourcesForCarRepository;
    private final FileResourcesForCarService fileResourcesForCarService;
    private final CarRepository carRepository;
    private final CarService carService;
    private final CarCategoryRepository carCategoryRepository;
    private final CarCategoryService carCategoryService;

    public CarCommandRunner(FileResourcesForCarRepository fileResourcesForCarRepository, FileResourcesForCarService fileResourcesForCarService, CarRepository carRepository, CarService carService, CarCategoryRepository carCategoryRepository, CarCategoryService carCategoryService) {
        this.fileResourcesForCarRepository = fileResourcesForCarRepository;
        this.fileResourcesForCarService = fileResourcesForCarService;
        this.carRepository = carRepository;
        this.carService = carService;
        this.carCategoryRepository = carCategoryRepository;
        this.carCategoryService = carCategoryService;
    }

    @Override
    public void run(String... args) throws Exception {
        CarCategory sportsCategory = createCategory(CarType.SPORT, 2, 1000.0);
        CarCategory sedanCategory = createCategory(CarType.SEDAN, 5, 800.0);
        CarCategory vanCategory = createCategory(CarType.VAN, 8, 600.0);

        Car ferrariF8 = createCarWithImages("Ferrari", "F8", 2021, sportsCategory);
        List<FileResourcesForCar> ferrariF8List = new ArrayList<>();
        addImage("ferrariF8-1.jpg", "/ferrariF8/ferrariF8Photo-1.jpg", ferrariF8, ferrariF8List);
        addImage("ferrariF8-2.jpg", "/ferrariF8/ferrariF8Photo-2.jpg", ferrariF8, ferrariF8List);
        addImage("ferrariF8-3.jpg", "/ferrariF8/ferrariF8Photo-3.jpg", ferrariF8, ferrariF8List);

        Car lamborghiniUrus = createCarWithImages("Lamborghini", "Urus", 2020, sportsCategory);
        List<FileResourcesForCar> lamborghiniUrusList = new ArrayList<>();
        addImage("lamborghiniUrusPhoto-1.jpg", "/lamborghiniUrus/lamborghiniUrusPhoto-1.jpg", lamborghiniUrus, lamborghiniUrusList);
        addImage("lamborghiniUrusPhoto-2.jpg", "/lamborghiniUrus/lamborghiniUrusPhoto-2.jpg", lamborghiniUrus, lamborghiniUrusList);
        addImage("lamborghiniUrusPhoto-3.jpg", "/lamborghiniUrus/lamborghiniUrusPhoto-3.jpg", lamborghiniUrus, lamborghiniUrusList);

        Car audiR8 = createCarWithImages("Audi", "R8", 2021, sportsCategory);
        List<FileResourcesForCar> audiR8List = new ArrayList<>();
        addImage("audiR8Photo-1.jpg", "/audiR8/audiR8Photo-1.jpg", audiR8, audiR8List);
        addImage("audiR8Photo-2.jpg", "/audiR8/audiR8Photo-2.jpg", audiR8, audiR8List);
        addImage("audiR8Photo-2.jpg", "/audiR8/audiR8Photo-3.jpg", audiR8, audiR8List);

        Car mercedesSClass = createCarWithImages("Mercedes", "S-Class", 2021, sedanCategory);
        List<FileResourcesForCar> mercedesSClassList = new ArrayList<>();
        addImage("audiR8Photo-1.jpg", "/audiR8/audiR8Photo-1.jpg", audiR8, audiR8List);
        addImage("audiR8Photo-2.jpg", "/audiR8/audiR8Photo-2.jpg", audiR8, audiR8List);
        addImage("audiR8Photo-2.jpg", "/audiR8/audiR8Photo-3.jpg", audiR8, audiR8List);


    }


    private Car createCarWithImages(String brand, String model, Integer year, CarCategory carCategory) {
        Car car = carService.findCarByBrandAndModel(brand, model);

        if (car == null) {
            car = Car.builder()
                    .brand(brand)
                    .model(model)
                    .manufacturingYear(year)
                    .carCategory(carCategory)
                    .build();
            carRepository.save(car);
        }

        return car;
    }

    private void addImage(String name, String path, Car car, List<FileResourcesForCar> imageList) throws IOException {
        FileResourcesForCar existingImage = fileResourcesForCarService.findFileByName(name);

        if (existingImage == null) {
            FileResourcesForCar newImage = new FileResourcesForCar();
            newImage.setImageName(name);

            try {
                byte[] imageData = ImageFileUtil.readImageDataFromFile("src/main/java/com/aacademy/moonlight/carImages" + path);
                newImage.setPhoto(imageData);
                newImage.setCar(car);
                fileResourcesForCarRepository.save(newImage);
                imageList.add(newImage);
            } catch (IOException e) {
                throw new RuntimeException("Failed to read image data from file: " + path, e);
            }
        } else {
            imageList.add(existingImage);
        }
    }

    private CarCategory createCategory(CarType type, Integer seats, Double price) {
        CarCategory carCategory = carCategoryService.findByType(type);

        if (carCategory == null) {
            carCategory = CarCategory.builder()
                    .type(type)
                    .seats(seats)
                    .price(price)
                    .build();
            carCategoryRepository.save(carCategory);
        }
        return carCategory;
    }
}
