package com.aacademy.moonlight.service.restaurant.impl;

import com.aacademy.moonlight.converter.restaurant.TableRestaurantConverter;
import com.aacademy.moonlight.dto.restaurant.TableRestaurantRequest;
import com.aacademy.moonlight.entity.restaurant.TableRestaurant;
import com.aacademy.moonlight.repository.restaurant.TableRestaurantRepository;
import com.aacademy.moonlight.service.restaurant.TableRestaurantService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TableRestaurantServiceImpl implements TableRestaurantService {

    private final TableRestaurantConverter converter;
    private final TableRestaurantRepository repository;

    public TableRestaurantServiceImpl(TableRestaurantConverter converter, TableRestaurantRepository repository) {
        this.converter = converter;
        this.repository = repository;
    }

    @Override
    public TableRestaurant saveTable(TableRestaurantRequest request) {
        TableRestaurant table = converter.toTable(request);
        return repository.save(table);
    }

    @Override
    public TableRestaurant findByNumber(Integer tableNumber) {
        List<TableRestaurant> tables = repository.findAll();
        TableRestaurant tableRestaurant = null;
        for (TableRestaurant table: tables) {
            if(table.getTableNumber() == tableNumber){
                tableRestaurant = table;
            }
        }
        return tableRestaurant;
    }

    @Override
    public TableRestaurant updateTableById(Long id, TableRestaurantRequest request) {
        TableRestaurant tableRestaurant = repository.findById(id).orElseThrow();

        if(request.getTableZone() != null){
            tableRestaurant.setTableZone(request.getTableZone());
        }
        if(request.getIs_Smoking() != null){
            tableRestaurant.setIs_Smoking(request.getIs_Smoking());
        }
        if(request.getSeats() != null){
            tableRestaurant.setSeats(tableRestaurant.getSeats());
        }
        if (request.getPrice() != null){
            tableRestaurant.setPrice(request.getPrice());
        }
        return tableRestaurant;
    }

    @Override
    public void deleteTableById(Long id) {
        repository.deleteById(id);
    }
}
