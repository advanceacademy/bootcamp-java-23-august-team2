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
        addImage("audiR8Photo-3.jpg", "/audiR8/audiR8Photo-3.jpg", audiR8, audiR8List);

        Car mercedesSClass = createCarWithImages("Mercedes", "S-Class", 2021, sedanCategory);
        List<FileResourcesForCar> mercedesSClassList = new ArrayList<>();
        addImage("mercedesSClassPhoto-1.jpg", "/mercedesSClass/mercedesSClassPhoto-1.jpg", mercedesSClass, mercedesSClassList);
        addImage("mercedesSClassPhoto-2.jpg", "/mercedesSClass/mercedesSClassPhoto-2.jpg", mercedesSClass, mercedesSClassList);
        addImage("mercedesSClassPhoto-3.jpg", "/mercedesSClass/mercedesSClassPhoto-3.jpg", mercedesSClass, mercedesSClassList);

        Car audiA8 = createCarWithImages("Audi", "A8", 2021, sedanCategory);
        List<FileResourcesForCar> audiA8List = new ArrayList<>();
        addImage("audiA8Photo-1.jpg", "/audiA8/audiA8Photo-1.jpg", audiA8, audiA8List);
        addImage("audiA8Photo-2.jpg", "/audiA8/audiA8Photo-2.jpg", audiA8, audiA8List);
        addImage("audiA8Photo-3.jpg", "/audiA8/audiA8Photo-3.jpg", audiA8, audiA8List);

        Car bmw5series = createCarWithImages("BMW", "5 Series", 2020, sedanCategory);
        List<FileResourcesForCar> bmw5seriesList = new ArrayList<>();
        addImage("bmw5seriesPhoto-1.jpg", "/bmw5series/bmw5seriesPhoto-1.jpg", bmw5series, bmw5seriesList);
        addImage("bmw5seriesPhoto-2.jpg", "/bmw5series/bmw5seriesPhoto-2.jpg", bmw5series, bmw5seriesList);
        addImage("bmw5seriesPhoto-3.jpg", "/bmw5series/bmw5seriesPhoto-3.jpg", bmw5series, bmw5seriesList);

        Car vwPassatB9 = createCarWithImages("Volkswagen", "Passat B9", 2021, sedanCategory);
        List<FileResourcesForCar> vwPassatB9List = new ArrayList<>();
        addImage("vwPassatB9Photo-1.jpg", "/vwPassatB9/vwPassatB9Photo-1.jpg", vwPassatB9, vwPassatB9List);
        addImage("vwPassatB9Photo-2.jpg", "/vwPassatB9/vwPassatB9Photo-2.jpg", vwPassatB9, vwPassatB9List);
        addImage("vwPassatB9Photo-3.jpg", "/vwPassatB9/vwPassatB9Photo-3.jpg", vwPassatB9, vwPassatB9List);

        Car mercedesVClass = createCarWithImages("Mercedes", "V-Class", 2021, vanCategory);
        List<FileResourcesForCar> mercedesVCLassList = new ArrayList<>();
        addImage("mercedesVClassPhoto-1.jpg", "/mercedesVClass/mercedesVClassPhoto-1.jpg", mercedesVClass, mercedesVCLassList);
        addImage("mercedesVClassPhoto-2.jpg", "/mercedesVClass/mercedesVClassPhoto-2.jpg", mercedesVClass, mercedesVCLassList);
        addImage("mercedesVClassPhoto-3.jpg", "/mercedesVClass/mercedesVClassPhoto-3.jpg", mercedesVClass, mercedesVCLassList);

        Car vwRLine = createCarWithImages("Volkswagen", "R Line", 2020, vanCategory);
        List<FileResourcesForCar> vwRLineList = new ArrayList<>();
        addImage("vwRLinePhoto-1.jpg", "/vwRLine/vwRLinePhoto-1.jpg", vwRLine, vwRLineList);
        addImage("vwRLinePhoto-2.jpg", "/vwRLine/vwRLinePhoto-2.jpg", vwRLine, vwRLineList);
        addImage("vwRLinePhoto-3.jpg", "/vwRLine/vwRLinePhoto-3.jpg", vwRLine, vwRLineList);

        Car bmwVan = createCarWithImages("BMW", "2 Series Gran Tourer", 2020, vanCategory);
        List<FileResourcesForCar> bmwVanList = new ArrayList<>();
        addImage("bmwVanPhoto-1.jpg", "/bmwVan/bmwVanPhoto-1.jpg", bmwVan, bmwVanList);
        addImage("bmwVanPhoto-2.jpg", "/bmwVan/bmwVanPhoto-2.jpg", bmwVan, bmwVanList);
        addImage("bmwVanPhoto-3.jpg", "/bmwVan/bmwVanPhoto-3.jpg", bmwVan, bmwVanList);

        Car hyundaiH1 = createCarWithImages("Hyundai", "H-1", 2020, vanCategory);
        List<FileResourcesForCar> hyundaiH1List = new ArrayList<>();
        addImage("hyundaiH1Photo-1.jpg", "/hyundaiH1/hyundaiH1Photo-1.jpg", hyundaiH1, hyundaiH1List);
        addImage("hyundaiH1Photo-2.jpg", "/hyundaiH1/hyundaiH1Photo-2.jpg", hyundaiH1, hyundaiH1List);
        addImage("hyundaiH1Photo-3.jpg", "/hyundaiH1/hyundaiH1Photo-3.jpg", hyundaiH1, hyundaiH1List);



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
