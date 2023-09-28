package com.aacademy.moonlight.controller.restaurant;

import com.aacademy.moonlight.dto.restaurant.TableRestaurantRequest;
import com.aacademy.moonlight.dto.restaurant.TableRestaurantResponse;
import com.aacademy.moonlight.entity.restaurant.TableRestaurant;
import com.aacademy.moonlight.entity.restaurant.TableZone;
import com.aacademy.moonlight.service.restaurant.TableRestaurantService;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/auth/tables")
public class TableController {

    @Autowired
    TableRestaurantService tableRestaurantService;

    @GetMapping("/zone/{zone}")
    public ResponseEntity<TableRestaurantResponse> getTablesByZone(@PathVariable TableZone tableZone) {
        List<TableRestaurantResponse> tablesZone = (List<TableRestaurantResponse>) tableRestaurantService.getTablesByZone();
        return ResponseEntity.status(HttpStatus.FOUND).body((TableRestaurantResponse) tablesZone);
    }

    @GetMapping("/smoking")
    public ResponseEntity<List<TableRestaurantResponse>> getSmokingTables(@RequestParam(name = "isSmokingAllowed", defaultValue = "true") boolean isSmokingAllowed) {
        List<TableRestaurantResponse> savedTables = tableRestaurantService.getSmokingTables(isSmokingAllowed);
        return ResponseEntity.status(HttpStatus.FOUND).body(savedTables);
        }


    @PutMapping("/{id}")
    public ResponseEntity<TableRestaurant> updateTableById(@PathVariable Long id, @Valid @RequestBody TableRestaurantRequest request) {
        TableRestaurant tableRestaurant = tableRestaurantService.updateTableById(id, request);
        return ResponseEntity.status(HttpStatus.FOUND).body(tableRestaurant);
    }

    @GetMapping("/table-number/{tableNumber}")
    public ResponseEntity<TableRestaurantResponse> getTableByTableNumber(@Valid @PathVariable Integer tableNumber) {
        TableRestaurantResponse tableRestaurantResponse = tableRestaurantService.getTableByTableNumber(tableNumber);
        return ResponseEntity.status(HttpStatus.FOUND).body(tableRestaurantResponse);
    }

    @GetMapping("/seats/{numberOfSeats}")
    public ResponseEntity<List<TableRestaurantResponse>> getTablesByNumberOfSeats(@Valid @PathVariable Integer numberOfSeats) {
        List<TableRestaurantResponse> list = tableRestaurantService.getTablesByNumberOfSeats(numberOfSeats);
        return ResponseEntity.status(HttpStatus.FOUND).body(list);
    }
}

