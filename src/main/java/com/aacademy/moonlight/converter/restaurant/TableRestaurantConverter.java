package com.aacademy.moonlight.converter.restaurant;

import com.aacademy.moonlight.dto.restaurant.TableRestaurantRequest;
import com.aacademy.moonlight.dto.restaurant.TableRestaurantResponse;
import com.aacademy.moonlight.dto.user.UserResponse;
import com.aacademy.moonlight.entity.restaurant.TableRestaurant;
import com.aacademy.moonlight.entity.restaurant.TableZone;
import com.aacademy.moonlight.entity.user.User;
import org.springframework.stereotype.Component;

@Component
public class TableRestaurantConverter {
    public TableRestaurant toTable(TableRestaurantRequest request) {
        return TableRestaurant.builder()
                .tableNumber(request.getTableNumber())
                .tableZone(request.getTableZone())
                .is_Smoking(request.getIs_Smoking())
                .seats(request.getSeats())
                .price(request.getPrice()).
                build();
    }

    public TableRestaurantResponse toTableRestaurantResponse(TableRestaurant tableRestaurant) {
        return new TableRestaurantResponse(tableRestaurant.getId(), tableRestaurant.getTableNumber(), tableRestaurant.getTableZone(), tableRestaurant.getIs_Smoking(), tableRestaurant.getSeats(), tableRestaurant.getPrice());


    }
}







