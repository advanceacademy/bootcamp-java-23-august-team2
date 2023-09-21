package com.aacademy.moonlight.service.restaurant.impl;

import com.aacademy.moonlight.converter.restaurant.TableRestaurantConverter;
import com.aacademy.moonlight.dto.restaurant.TableRestaurantRequest;
import com.aacademy.moonlight.dto.restaurant.TableRestaurantResponse;
import com.aacademy.moonlight.entity.restaurant.TableRestaurant;
import com.aacademy.moonlight.entity.restaurant.TableZone;
import com.aacademy.moonlight.repository.restaurant.TableRestaurantRepository;
import com.aacademy.moonlight.service.restaurant.TableRestaurantService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    public TableRestaurant findByNumber(Integer tableNumber, TableZone tableZone) {
        List<TableRestaurant> tables = repository.findAll();
        TableRestaurant tableRestaurant = null;
        for (TableRestaurant table: tables) {
            if(table.getTableNumber() == tableNumber && table.getTableZone()== tableZone){
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

    @Override
    public List<TableRestaurant> getAllTables() {
        return null;
    }

    @Override
    public TableRestaurantResponse getTablesByZone() {
        List<TableRestaurant> tables = repository.findAll();

        List<TableRestaurantResponse> responses = new ArrayList<>();

        for (TableZone zone : TableZone.values()) {
            List<TableRestaurantResponse> zoneTables = new ArrayList<>();

            for (TableRestaurant tableRestaurant : tables) {
                if (tableRestaurant.getTableZone() == zone) {
                    TableRestaurantResponse response = new TableRestaurantResponse();
                    response.setTableNumber(tableRestaurant.getTableNumber());
                    zoneTables.add(response);
                }
            }

            responses.addAll(zoneTables);
        }

        return (TableRestaurantResponse) responses;
    }

    @Override
    public List<TableRestaurantResponse> getSmokingTables(boolean isSmokingAllowed) {
        List<TableRestaurant> allTables = repository.findAll();
        List<TableRestaurantResponse> smokingTableResponses = new ArrayList<>();

        for (TableRestaurant table : allTables) {
            if (isSmokingAllowed) {
                TableRestaurantResponse savedTable = converter.toTableRestaurantResponse(table);

                smokingTableResponses.add(savedTable);
            }
        }

        return smokingTableResponses;
    }

    @Override
    public TableRestaurantResponse getTableById(Long id) {

        TableRestaurant tableRestaurant = repository.findById(id).orElseThrow(()-> new RuntimeException("Table not found!"));
        return converter.toTableRestaurantResponse(tableRestaurant);
    }
    @Override
    public TableRestaurantResponse getTableByTableNumber(Integer tableNumber) {
        TableRestaurant tableRestaurant = repository.getTableByTableNumber().orElseThrow(()->
                new RuntimeException("Room with this number don't exist"));

        return converter.toTableRestaurantResponse(tableRestaurant);

    }
    @Override
    public List<TableRestaurantResponse> getTablesByNumberOfSeats(Integer numberOfSeats) {
        List<TableRestaurant> tableRestaurantList = repository.findAll();
        return tableRestaurantList.stream()
                .filter(tableRestaurant -> tableRestaurant.getSeats().equals(numberOfSeats))
                .map(converter::toTableRestaurantResponse)
                .toList();



    }


}





