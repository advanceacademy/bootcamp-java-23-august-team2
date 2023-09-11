package com.aacademy.moonlight.runner;

import com.aacademy.moonlight.dto.user.UserRequest;
import com.aacademy.moonlight.dto.user.UserResponse;
import com.aacademy.moonlight.repository.restaurant.TableReservationRepository;
import com.aacademy.moonlight.repository.restaurant.TableRestaurantRepository;
import com.aacademy.moonlight.repository.user.UserRepository;
import com.aacademy.moonlight.repository.user.UserRoleRepository;
import com.aacademy.moonlight.service.restaurant.TableRestaurantService;
import com.aacademy.moonlight.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CommandRunner implements CommandLineRunner {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserRoleRepository userRoleRepository;

    @Autowired
    TableReservationRepository tableReservationRepository;

    @Autowired
    TableRestaurantRepository tableRestaurantRepository;

    private final UserService userService;

    private final TableRestaurantService tableRestaurantService;


    @Autowired
    public CommandRunner(UserService userService, TableRestaurantService tableRestaurantService) {
        this.userService = userService;
        this.tableRestaurantService = tableRestaurantService;
    }




    @Override
    public void run(String... args) throws Exception {
//        TableRestaurant table = new TableRestaurant();
//        table.setTableNumber(1); // Set the table properties as needed.
//        table.setTableZone(TableZone.SALOON);
//        table.setIs_Smoking(false);
//        table.setSeats(4);
//        table.setPrice(20.0);
//
//        // Save the object to the database using the service
//
//        TableRestaurant savedTable = tableRestaurantRepository.save(table);






    }



}
