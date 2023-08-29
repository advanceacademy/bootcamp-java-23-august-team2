package com.aacademy.moonlight.repository.car;

import com.aacademy.moonlight.entity.car.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {
}
