package com.aacademy.moonlight.controller.restaurant;

import com.aacademy.moonlight.converter.restaurant.TableReservationConverter;
import com.aacademy.moonlight.dto.restaurant.TableReservationRequest;
import com.aacademy.moonlight.dto.restaurant.TableReservationResponse;
import com.aacademy.moonlight.entity.restaurant.TableRestaurant;
import com.aacademy.moonlight.service.restaurant.TableReservationService;
import com.aacademy.moonlight.service.restaurant.TableRestaurantService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity<TableReservationResponse> tableReservation(@Valid @RequestBody TableReservationRequest request) {
        Long tableId = request.getTableRestaurant().getId();

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
    @GetMapping(path = "/find-user-table-reservations")
    public ResponseEntity<List<TableReservationResponse>> getAllUserTableReservations(){
        return ResponseEntity.status(HttpStatus.FOUND).body(tableReservationService.getTableReservationsByUser());
    }
    @GetMapping(path = "/find-table-reservation-{id}")
    public ResponseEntity<TableReservationResponse> getTableReservationById(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.FOUND).body(tableReservationService.getTableReservationById(id));
    }
}
