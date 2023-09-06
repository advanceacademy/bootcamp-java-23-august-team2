package com.aacademy.moonlight.runner;

import com.aacademy.moonlight.dto.restaurant.TableRestaurantRequest;
import com.aacademy.moonlight.entity.restaurant.TableRestaurant;
import com.aacademy.moonlight.entity.restaurant.TableZone;
import com.aacademy.moonlight.repository.restaurant.TableRestaurantRepository;
import com.aacademy.moonlight.service.restaurant.TableRestaurantService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class TableRestaurantCommandRunner  implements CommandLineRunner{

    private final TableRestaurantRepository tableRestaurantRepository;

    private final TableRestaurantService tableRestaurantService;

    public TableRestaurantCommandRunner(TableRestaurantRepository tableRestaurantRepository, TableRestaurantService tableRestaurantService) {
        this.tableRestaurantRepository = tableRestaurantRepository;
        this.tableRestaurantService = tableRestaurantService;
    }

    @Override
    public void run(String... args) throws Exception {

       // createBarSeats();

        // Creating Bar seats
        createTables(TableZone.BAR,1, 1, 11, false);

        // Creating terrace tables
        createTables(TableZone.TERRACE,4, 11, 16, true);

        // Creating Saloon Tables 1-5
        createTables(TableZone.SALOON, 4, 1, 5, false);

        // Creating Saloon Tables 6 and 10
        createTables(TableZone.SALOON, 2, 6, 6, false);
        createTables(TableZone.SALOON, 2, 10, 10, false);

        // Creating Saloon Tables 7 and 8
        createTables(TableZone.SALOON, 7, 7, 8, false);

        // Creating Saloon Table 9
        createTables(TableZone.SALOON, 8, 9, 9, false);

    }

    private void createTables (TableZone tableZone, int seats, int firstTable, int lastTable, boolean isSmoking) {
        for (int i = firstTable; i <= lastTable ; i++) {
            TableRestaurantRequest table = TableRestaurantRequest.builder()
                    .tableZone(tableZone)
                    .seats(seats)
                    .tableNumber(i)
                    .price(10.0)
                    .is_Smoking(isSmoking)
                    .build();
            TableRestaurant searchedTable = tableRestaurantService.findByNumber(table.getTableNumber(), tableZone);
            if (Objects.isNull(searchedTable)) {
                tableRestaurantService.saveTable(table);
            }
        }
    }
}
