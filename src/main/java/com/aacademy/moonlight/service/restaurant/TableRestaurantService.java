package com.aacademy.moonlight.service.restaurant;

import com.aacademy.moonlight.dto.restaurant.TableRestaurantRequest;
import com.aacademy.moonlight.dto.restaurant.TableRestaurantResponse;
import com.aacademy.moonlight.entity.restaurant.TableRestaurant;
import com.aacademy.moonlight.entity.restaurant.TableZone;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TableRestaurantService {
    TableRestaurant saveTable(TableRestaurantRequest request);
    TableRestaurant findByNumber(Integer tableNumber, TableZone tableZone);
    TableRestaurant updateTableById(Long id, TableRestaurantRequest request);
    void deleteTableById(Long id);
    List<TableRestaurant> getAllTables();
    TableRestaurantResponse getTablesByZone();
    List<TableRestaurantResponse> getSmokingTables();


    TableRestaurantResponse getTableById(Long id);

    TableRestaurantResponse getTableByTableNumber(Integer tableNumber);
    List<TableRestaurantResponse> getTablesByNumberOfSeats(Integer numberOfSeats);


    List<TableRestaurant> getSmokingTables(boolean isSmokingAllowed);
}
