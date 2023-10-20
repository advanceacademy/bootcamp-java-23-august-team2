package com.aacademy.moonlight.service.restaurant.impl;

import com.aacademy.moonlight.converter.restaurant.TableRestaurantConverter;
import com.aacademy.moonlight.dto.restaurant.TableRestaurantRequest;
import com.aacademy.moonlight.dto.restaurant.TableRestaurantResponse;
import com.aacademy.moonlight.entity.restaurant.TableRestaurant;
import com.aacademy.moonlight.entity.restaurant.TableZone;
import com.aacademy.moonlight.repository.restaurant.TableReservationRepository;
import com.aacademy.moonlight.repository.restaurant.TableRestaurantRepository;
import com.aacademy.moonlight.service.restaurant.TableRestaurantService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class TableRestaurantServiceImpl implements TableRestaurantService {

    private final TableRestaurantConverter converter;
    private final TableRestaurantRepository repository;
    private final TableReservationRepository reservationRepository;

    public TableRestaurantServiceImpl(TableRestaurantConverter converter,
                                      TableRestaurantRepository repository,
                                      TableReservationRepository reservationRepository) {
        this.converter = converter;
        this.repository = repository;
        this.reservationRepository = reservationRepository;
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
        for (TableRestaurant table : tables) {
            if (table.getTableNumber() == tableNumber && table.getTableZone() == tableZone) {
                tableRestaurant = table;
            }
        }
        return tableRestaurant;
    }

    @Override
    public TableRestaurant findById(Long id) {
        return repository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Table with id = "+ id +" not found.")
        );
    }

    @Override
    public TableRestaurant updateTableById(Long id, TableRestaurantRequest request) {
        TableRestaurant tableRestaurant = repository.findById(id).orElseThrow();

        if (request.getTableZone() != null) {
            tableRestaurant.setTableZone(request.getTableZone());
        }
        if (request.getIs_Smoking() != null) {
            tableRestaurant.setIs_Smoking(request.getIs_Smoking());
        }
        if (request.getSeats() != null) {
            tableRestaurant.setSeats(tableRestaurant.getSeats());
        }
        if (request.getPrice() != null) {
            tableRestaurant.setPrice(request.getPrice());
        }
        return tableRestaurant;
    }

    @Override
    public void deleteTableById(Long id) {
        repository.deleteById(id);
    }


    @Override
    public List<TableRestaurantResponse> getTablesByZone(String tableZone) {
        TableZone zone;

        if (tableZone.equalsIgnoreCase("bar")) {
            zone = TableZone.BAR;
        } else if (tableZone.equalsIgnoreCase("terrace")) {
            zone = TableZone.TERRACE;
        } else if (tableZone.equalsIgnoreCase("saloon")) {
            zone = TableZone.SALOON;

        } else {
            throw new EntityNotFoundException("Our restaurant has three zones: Bar, Terrace and Saloon");
        }
        List<TableRestaurantResponse> tables = new ArrayList<>();
        List<TableRestaurant> allTables = repository.findAll();

        for (TableRestaurant tableRestaurant : allTables) {
            if (tableRestaurant.getTableZone().equals(zone)) {
                tables.add(converter.toTableRestaurantResponse(tableRestaurant));
            }
        }
        if (tables.isEmpty()) {
            throw new EntityNotFoundException(tableZone + " was not found ");
        }
        return tables;
    }


    @Override
    public List<TableRestaurantResponse> getSmokingTables(boolean isSmokingAllowed) {
        List<TableRestaurant> allTables = repository.findAll();
        List<TableRestaurantResponse> smokingTableResponses = new ArrayList<>();
        List<TableRestaurantResponse> noSmokingTableResponse = new ArrayList<>();

        for (TableRestaurant tableRestaurant : allTables) {
            if (tableRestaurant.getIs_Smoking()) {
                smokingTableResponses.add(converter.toTableRestaurantResponse(tableRestaurant));
            } else {
                noSmokingTableResponse.add(converter.toTableRestaurantResponse(tableRestaurant));
            }
        }
        if (isSmokingAllowed){
            return smokingTableResponses;
        }
        else {
            return noSmokingTableResponse;
        }

    }

    @Override
    public List<TableRestaurant> getAvailableTables(LocalDate date, LocalDateTime time, String zone, Integer people) {
        return null;
    }

    @Override
    public TableRestaurantResponse getTableById(Long id) {

        TableRestaurant tableRestaurant = repository.findById(id).orElseThrow(() -> new RuntimeException("Table not found!"));
        return converter.toTableRestaurantResponse(tableRestaurant);
    }

    @Override
    public TableRestaurantResponse getTableByTableNumber(Integer tableNumber) {
        List<TableRestaurant> tableRestaurantList = repository.findAll();
        TableRestaurant currentTable = null;
        for (TableRestaurant table : tableRestaurantList) {
            if (table.getTableNumber().equals(tableNumber)) {
                currentTable = table;
            }
        }

        if (currentTable == null) {
            throw new EntityNotFoundException("Table with this number isn't found");
        }

        return converter.toTableRestaurantResponse(currentTable);

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





