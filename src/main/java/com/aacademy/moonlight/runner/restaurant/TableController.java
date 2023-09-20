package com.aacademy.moonlight.runner.restaurant;

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
@RequestMapping("/tables")
public class TableController {

    @Autowired
    TableRestaurantService tableRestaurantService;

    @GetMapping("/save-Table")
    public TableRestaurant saveTable(@Valid @RequestBody TableRestaurantRequest request) {

        return tableRestaurantService.saveTable(request);
    }

    @GetMapping("/zone/{zone}")
    public ResponseEntity<TableRestaurantResponse> getTablesByZone(@PathVariable TableZone tableZone) {
        TableRestaurantResponse tableRestaurantResponse = tableRestaurantService.getTablesByZone();
        return ResponseEntity.status(HttpStatus.FOUND).body(tableRestaurantResponse);
    }

    @GetMapping("/smoking")
    public ResponseEntity<List<TableRestaurantResponse>> getSmokingTables(@RequestParam(name = "isSmokingAllowed", defaultValue = "true") boolean isSmokingAllowed) {
        List<TableRestaurant> allTables = tableRestaurantService.getSmokingTables(isSmokingAllowed);
        List<TableRestaurantResponse> smokingTableResponses = new ArrayList<>();

        for (TableRestaurant table : allTables) {
            if (table.isSmokingAllowed() == isSmokingAllowed) {
                TableRestaurantResponse response = new TableRestaurantResponse();
                response.setTableNumber(table.getTableNumber());
                smokingTableResponses.add(response);
            }
        }

        if (!smokingTableResponses.isEmpty()) {
            return ResponseEntity.ok(smokingTableResponses);
        } else {

            return ResponseEntity.notFound().build();
        }
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
    public ResponseEntity<TableRestaurantResponse> getTablesByNumberOfSeats(@Valid @PathVariable Integer numberOfSeats) {
        TableRestaurant tableRestaurant = (TableRestaurant) tableRestaurantService.getTablesByNumberOfSeats(numberOfSeats);
        return ResponseEntity.status(HttpStatus.FOUND).body((TableRestaurantResponse) tableRestaurantService.getTablesByNumberOfSeats(numberOfSeats));
    }
}

