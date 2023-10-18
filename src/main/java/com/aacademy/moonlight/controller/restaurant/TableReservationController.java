package com.aacademy.moonlight.controller.restaurant;

import com.aacademy.moonlight.converter.restaurant.TableReservationConverter;
import com.aacademy.moonlight.dto.restaurant.TableReservationRequest;
import com.aacademy.moonlight.dto.restaurant.TableReservationResponse;
import com.aacademy.moonlight.entity.restaurant.TableRestaurant;
import com.aacademy.moonlight.entity.user.User;
import com.aacademy.moonlight.service.restaurant.TableReservationService;
import com.aacademy.moonlight.service.restaurant.TableRestaurantService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/client/table-reservation")
public class TableReservationController {
    @Autowired
    TableReservationService tableReservationService;
    @Autowired
    TableRestaurantService tableService;
    @Autowired
    TableReservationConverter converter;

    @PostMapping("/reserve")
    public ResponseEntity<TableReservationResponse> tableReservation(@RequestBody TableReservationRequest request) {
        Long tableId = request.getTableRestaurant().getId();

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        request.setUser((User) auth.getPrincipal());

        TableRestaurant table = tableService.findById(tableId);

        if (table == null) {
            throw new EntityNotFoundException("Table with id " + tableId + " was not found.");
        }
        request.setTableRestaurant(table);
        request.setTotalPrice(request.getCountPeople() * 10.0);

        return ResponseEntity.status(HttpStatus.CREATED).body(converter.toTableReservationResponse(
                tableReservationService.bookReservation(request))
        );
    }
}
