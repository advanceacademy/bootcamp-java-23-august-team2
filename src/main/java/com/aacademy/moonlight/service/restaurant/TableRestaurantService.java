package com.aacademy.moonlight.service.restaurant;

import com.aacademy.moonlight.dto.restaurant.TableRestaurantRequest;
import com.aacademy.moonlight.dto.restaurant.TableRestaurantResponse;
import com.aacademy.moonlight.entity.restaurant.TableRestaurant;
import com.aacademy.moonlight.entity.restaurant.TableZone;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;


public interface TableRestaurantService {
    TableRestaurant saveTable(TableRestaurantRequest request);
    TableRestaurant findByNumber(Integer tableNumber, TableZone tableZone);
    TableRestaurant findById(Long id);
    TableRestaurant updateTableById(Long id, TableRestaurantRequest request);
    void deleteTableById(Long id);

    List<TableRestaurantResponse> getTablesByZone(String tableZone);
    TableRestaurantResponse getTableById(Long id);
    TableRestaurantResponse getTableByTableNumber(Integer tableNumber);
    List<TableRestaurantResponse> getTablesByNumberOfSeats(Integer numberOfSeats);
    List<TableRestaurantResponse> getSmokingTables(boolean isSmokingAllowed);
}
