package com.aacademy.moonlight.service.restaurant;

import com.aacademy.moonlight.dto.restaurant.TableRestaurantRequest;
import com.aacademy.moonlight.entity.restaurant.TableRestaurant;
import org.springframework.stereotype.Service;

@Service
public interface TableRestaurantService {
    TableRestaurant saveTable(TableRestaurantRequest request);
    TableRestaurant findByNumber(Integer tableNumber);
    TableRestaurant updateTableById(Long id, TableRestaurantRequest request);
    void deleteTableById(Long id);
}
