package com.aacademy.moonlight.converter.restaurant;

import com.aacademy.moonlight.dto.restaurant.TableRestaurantRequest;
import com.aacademy.moonlight.entity.restaurant.TableRestaurant;
import org.springframework.stereotype.Component;

@Component
public class TableRestaurantConverter {
    public TableRestaurant toTable(TableRestaurantRequest request){
        return TableRestaurant.builder()
                .tableNumber(request.getTableNumber())
                .tableZone(request.getTableZone())
                .is_Smoking(request.getIs_Smoking())
                .seats(request.getSeats())
                .price(request.getPrice()).
                build();
    }
}
