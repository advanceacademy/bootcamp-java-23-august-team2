package com.aacademy.moonlight.repository.car;

import com.aacademy.moonlight.entity.car.CarCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarCategoryRepository extends JpaRepository<CarCategory, Long> {
}
