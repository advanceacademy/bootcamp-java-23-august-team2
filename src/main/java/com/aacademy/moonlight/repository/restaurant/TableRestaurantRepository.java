package com.aacademy.moonlight.repository.restaurant;

import com.aacademy.moonlight.entity.restaurant.TableRestaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TableRestaurantRepository extends JpaRepository<TableRestaurant, Long> {

    Optional <TableRestaurant> findByTableNumber();


//    List<TableRestaurant> findByNumberOfSeats(Integer numberOfSeats);
}
