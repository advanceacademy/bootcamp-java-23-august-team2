package com.aacademy.moonlight.controller.restaurant;

import com.aacademy.moonlight.dto.restaurant.TableReservationRequest;
import com.aacademy.moonlight.dto.restaurant.TableReservationResponse;
import com.aacademy.moonlight.entity.restaurant.TableRestaurant;
import com.aacademy.moonlight.entity.user.User;
import com.aacademy.moonlight.service.restaurant.TableReservationService;
import com.aacademy.moonlight.service.restaurant.TableRestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/client/table-reservation")
public class TableReservationController {
    @Autowired
    TableReservationService tableReservationService;
    @Autowired
    TableRestaurantService tableService;

    public ResponseEntity<TableReservationResponse> tableReservation(@RequestBody TableReservationRequest request){
        Long tableId = request.getTableRestaurant().getId();

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        request.setUser((User) auth.getPrincipal());

//        TableRestaurant table = tableService.getTableById(tableId);
        return null;
    }
}
