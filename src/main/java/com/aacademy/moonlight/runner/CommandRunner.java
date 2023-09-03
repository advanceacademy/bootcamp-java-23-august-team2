package com.aacademy.moonlight.runner;

import com.aacademy.moonlight.entity.restaurant.TableRestaurant;
import com.aacademy.moonlight.entity.restaurant.TableZone;
import com.aacademy.moonlight.repository.restaurant.TableReservationRepository;
import com.aacademy.moonlight.repository.restaurant.TableRestaurantRepository;
import com.aacademy.moonlight.service.restaurant.TableRestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CommandRunner implements CommandLineRunner {

    @Autowired
    TableReservationRepository tableReservationRepository;

    @Autowired
    TableRestaurantRepository tableRestaurantRepository;

    private final TableRestaurantService tableRestaurantService;

    @Autowired
    public CommandRunner(TableRestaurantService tableRestaurantService) {
        this.tableRestaurantService = tableRestaurantService;
    }

    @Override
    public void run(String... args) throws Exception {
        TableRestaurant table = new TableRestaurant();
        table.setTableNumber(1); // Set the table properties as needed.
        table.setTableZone(TableZone.SALOON);
        table.setIs_Smoking(false);
        table.setSeats(4);
        table.setPrice(20.0);

        // Save the object to the database using the service

        TableRestaurant savedTable = tableRestaurantRepository.save(table);

    }
}
