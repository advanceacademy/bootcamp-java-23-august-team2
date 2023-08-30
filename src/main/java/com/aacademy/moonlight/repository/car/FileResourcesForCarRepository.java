package com.aacademy.moonlight.repository.car;

import com.aacademy.moonlight.entity.car.FileResourcesForCar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileResourcesForCarRepository extends JpaRepository<FileResourcesForCar, Long> {
}
