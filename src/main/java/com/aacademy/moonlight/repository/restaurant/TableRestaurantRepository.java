package com.aacademy.moonlight.repository.restaurant;

import com.aacademy.moonlight.entity.restaurant.TableRestaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TableRestaurantRepository extends JpaRepository<TableRestaurant, Long> {
}
