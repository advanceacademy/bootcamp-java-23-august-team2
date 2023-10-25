package com.aacademy.moonlight.converter.restaurant;

import com.aacademy.moonlight.dto.restaurant.TableReservationRequest;
import com.aacademy.moonlight.dto.restaurant.TableReservationResponse;
import com.aacademy.moonlight.entity.restaurant.TableReservation;
import org.springframework.stereotype.Component;

@Component
public class TableReservationConverter {
    public TableReservation toReservation(TableReservationRequest request){
        return TableReservation.builder()
                .date(request.getDate())
                .hour(request.getHour())
                .countPeople(request.getCountPeople())
                .totalPrice(request.getTotalPrice())
                .tableRestaurant(request.getTableRestaurant())
                .user(request.getUser())
                .paymentStatus(request.getPaymentStatus())
                .build();
    }

    public TableReservationResponse toTableReservationResponse (TableReservation tableReservation){
        return new TableReservationResponse(
                tableReservation.getDate(),
                tableReservation.getHour(),
                tableReservation.getCountPeople(),
                tableReservation.getTotalPrice(),
                tableReservation.getTableRestaurant(),
                tableReservation.getUser().getFirstName() + " " + tableReservation.getUser().getLastName(),
                tableReservation.getPaymentStatus());


    }
}
