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

        Car ferrariF8 = createCarWithImages("Ferrari", "F8", 2021, sportsCategory,
                "ferrariF8-1.jpg", "/ferrariF8/ferrariF8Photo-1.jpg",
                "ferrariF8-2.jpg", "/ferrariF8/ferrariF8Photo-2.jpg",
                "ferrariF8-3.jpg", "/ferrariF8/ferrariF8Photo-3.jpg"
        );

        Car lamborghiniUrus = createCarWithImages("Lamborghini", "Urus", 2020, sportsCategory,
                "lamborghiniUrusPhoto-1.jpg", "/lamborghiniUrus/lamborghiniUrusPhoto-1.jpg",
                "lamborghiniUrusPhoto-2.jpg", "/lamborghiniUrus/lamborghiniUrusPhoto-2.jpg",
                "lamborghiniUrusPhoto-3.jpg", "/lamborghiniUrus/lamborghiniUrusPhoto-3.jpg"
        );

        Car audiR8 = createCarWithImages("Audi", "R8", 2021, sportsCategory,
                "audiR8Photo-1.jpg", "/audiR8/audiR8Photo-1.jpg",
                "audiR8Photo-2.jpg", "/audiR8/audiR8Photo-2.jpg",
                "audiR8Photo-3.jpg", "/audiR8/audiR8Photo-3.jpg"
        );

        Car mercedesSClass = createCarWithImages("Mercedes", "S-Class", 2021, sedanCategory,
                "mercedesSClassPhoto-1.jpg", "/mercedesSClass/mercedesSClassPhoto-1.jpg",
                "mercedesSClassPhoto-2.jpg", "/mercedesSClass/mercedesSClassPhoto-2.jpg",
                "mercedesSClassPhoto-3.jpg", "/mercedesSClass/mercedesSClassPhoto-3.jpg"
        );

        Car audiA8 = createCarWithImages("Audi", "A8", 2021, sedanCategory,
                "audiA8Photo-1.jpg", "/audiA8/audiA8Photo-1.jpg",
                "audiA8Photo-2.jpg", "/audiA8/audiA8Photo-2.jpg",
                "audiA8Photo-3.jpg", "/audiA8/audiA8Photo-3.jpg"
        );

        Car bmw5series = createCarWithImages("BMW", "5 Series", 2020, sedanCategory,
                "bmw5seriesPhoto-1.jpg", "/bmw5series/bmw5seriesPhoto-1.jpg",
                "bmw5seriesPhoto-2.jpg", "/bmw5series/bmw5seriesPhoto-2.jpg",
                "bmw5seriesPhoto-3.jpg", "/bmw5series/bmw5seriesPhoto-3.jpg"
        );

        Car vwPassatB9 = createCarWithImages("Volkswagen", "Passat B9", 2021, sedanCategory,
                "vwPassatB9Photo-1.jpg", "/vwPassatB9/vwPassatB9Photo-1.jpg",
                "vwPassatB9Photo-2.jpg", "/vwPassatB9/vwPassatB9Photo-2.jpg",
                "vwPassatB9Photo-3.jpg", "/vwPassatB9/vwPassatB9Photo-3.jpg"
        );

        Car mercedesVClass = createCarWithImages("Mercedes", "V-Class", 2021, vanCategory,
                "mercedesVClassPhoto-1.jpg", "/mercedesVClass/mercedesVClassPhoto-1.jpg",
                "mercedesVClassPhoto-2.jpg", "/mercedesVClass/mercedesVClassPhoto-2.jpg",
                "mercedesVClassPhoto-3.jpg", "/mercedesVClass/mercedesVClassPhoto-3.jpg"
        );

        Car vwRLine = createCarWithImages("Volkswagen", "R Line", 2020, vanCategory,
                "vwRLinePhoto-1.jpg", "/vwRLine/vwRLinePhoto-1.jpg",
                "vwRLinePhoto-2.jpg", "/vwRLine/vwRLinePhoto-2.jpg",
                "vwRLinePhoto-3.jpg", "/vwRLine/vwRLinePhoto-3.jpg"
        );

        Car bmwVan = createCarWithImages("BMW", "2 Series Gran Tourer", 2020, vanCategory,
                "bmwVanPhoto-1.jpg", "/bmwVan/bmwVanPhoto-1.jpg",
                "bmwVanPhoto-2.jpg", "/bmwVan/bmwVanPhoto-2.jpg",
                "bmwVanPhoto-3.jpg", "/bmwVan/bmwVanPhoto-3.jpg"
        );

        Car hyundaiH1 = createCarWithImages("Hyundai", "H-1", 2020, vanCategory,
                "hyundaiH1Photo-1.jpg", "/hyundaiH1/hyundaiH1Photo-1.jpg",
                "hyundaiH1Photo-2.jpg", "/hyundaiH1/hyundaiH1Photo-2.jpg",
                "hyundaiH1Photo-3.jpg", "/hyundaiH1/hyundaiH1Photo-3.jpg"
        );

        List<FileResourcesForCar> ferrariF8Photos = ferrariF8.getFileResourcesForCar();

        for (FileResourcesForCar photo : ferrariF8Photos) {
            System.out.println("Photo Name: " + photo.getImageName());
        }

    }

    private Car createCarWithImages(String brand, String model, Integer year, CarCategory carCategory, String... imageInfo) {
        if (imageInfo.length % 2 != 0) {
            throw new IllegalArgumentException("Image info should be provided as pairs of name and path.");
        }

        Car car = carService.findCarByBrandAndModel(brand, model);

        if (car == null) {
            car = Car.builder()
                    .brand(brand)
                    .model(model)
                    .manufacturingYear(year)
                    .carCategory(carCategory)
                    .build();
            carRepository.save(car);

            List<FileResourcesForCar> imageList = new ArrayList<>();

            for (int i = 0; i < imageInfo.length; i += 2) {
                String imageName = imageInfo[i];
                String imagePath = imageInfo[i + 1];
                FileResourcesForCar image = addImage(imageName, imagePath, car);
                imageList.add(image);
            }

            car.setFileResourcesForCar(imageList);
        }

        return car;
    }

    private FileResourcesForCar addImage(String name, String path, Car car) {
        FileResourcesForCar existingImage = fileResourcesForCarService.findFileByName(name);

        if (existingImage == null) {
            FileResourcesForCar newImage = new FileResourcesForCar();
            newImage.setImageName(name);

            try {
                byte[] imageData = ImageFileUtil.readImageDataFromFile("src/main/java/com/aacademy/moonlight/carImages" + path);
                newImage.setPhoto(imageData);
                newImage.setCar(car);
                return fileResourcesForCarRepository.save(newImage);
            } catch (IOException e) {
                throw new RuntimeException("Failed to read image data from file: " + path, e);
            }
        } else {
            return existingImage;
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
