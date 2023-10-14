package com.aacademy.moonlight.service.restaurant.impl;

import com.aacademy.moonlight.converter.restaurant.TableReservationConverter;
import com.aacademy.moonlight.dto.restaurant.TableReservationRequest;
import com.aacademy.moonlight.dto.restaurant.TableReservationResponse;
import com.aacademy.moonlight.dto.user.UserResponse;
import com.aacademy.moonlight.entity.restaurant.TableReservation;
import com.aacademy.moonlight.entity.restaurant.TableRestaurant;
import com.aacademy.moonlight.entity.user.User;
import com.aacademy.moonlight.repository.restaurant.TableReservationRepository;
import com.aacademy.moonlight.service.restaurant.TableReservationService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TableReservationServiceImpl  implements TableReservationService {
    private final TableReservationRepository repository;
    private final TableReservationConverter converter;

    public TableReservationServiceImpl(TableReservationRepository repository, TableReservationConverter converter) {
        this.repository = repository;
        this.converter = converter;
    }

    @Override
    public TableReservation bookReservation(TableReservationRequest request) {
        TableReservation reservation = converter.toReservation(request);
        return repository.save(reservation);
    }

    @Override
    public TableReservation updateReservation(Long id, TableReservationRequest request) {
        TableReservation tableReservation = repository.findById(id).orElseThrow();

        TableRestaurant table = request.getTableRestaurant();
        User user = request.getUser();

        if(request.getDate() != null){
            tableReservation.setDate(request.getDate());
        }
        if(request.getHour() != null){
            tableReservation.setHour(request.getHour());
        }
        if(request.getCountPeople() != null){
            tableReservation.setCountPeople(request.getCountPeople());
        }
        if(request.getTotalPrice() != null){
            tableReservation.setTotalPrice(request.getTotalPrice());
        }
        if(table != null){
            tableReservation.setTableRestaurant(table);
        }
        if(user != null){
            tableReservation.setUser(user);
        }
        if(request.getPaymentStatus() != null){
            tableReservation.setPaymentStatus(request.getPaymentStatus());
        }
        return repository.save(tableReservation);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public List<TableReservationResponse> getAllTableReservations() {
        List<TableReservation> tableResrvationList = repository.findAll();
        List<TableReservationResponse> allTableReservations = new ArrayList<>();
        for(TableReservation tableReservation : tableResrvationList){
            TableReservationResponse response = converter.toTableReservationResponse(tableReservation);
            allTableReservations.add(response);
        }
        return allTableReservations;
    }
}
