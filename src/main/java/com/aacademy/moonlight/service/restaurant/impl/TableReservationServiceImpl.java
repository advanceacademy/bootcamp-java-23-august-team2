package com.aacademy.moonlight.service.restaurant.impl;

import com.aacademy.moonlight.converter.restaurant.TableReservationConverter;
import com.aacademy.moonlight.dto.restaurant.TableReservationRequest;
import com.aacademy.moonlight.entity.restaurant.TableReservation;
import com.aacademy.moonlight.entity.restaurant.TableRestaurant;
import com.aacademy.moonlight.entity.user.User;
import com.aacademy.moonlight.exceptions.BadRequestException;
import com.aacademy.moonlight.repository.restaurant.TableReservationRepository;
import com.aacademy.moonlight.service.restaurant.TableReservationService;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class TableReservationServiceImpl implements TableReservationService {
    private final TableReservationRepository repository;
    private final TableReservationConverter converter;

    public TableReservationServiceImpl(TableReservationRepository repository, TableReservationConverter converter) {
        this.repository = repository;
        this.converter = converter;
    }

    @Override
    public TableReservation bookReservation(TableReservationRequest request) {
        TableReservation tableReservation = converter.toReservation(request);
        List<TableReservation> reservations = repository.findAll();

        for (TableReservation reservation : reservations) {
            if (Objects.equals(tableReservation.getTableRestaurant(), reservation.getTableRestaurant())
                    && Objects.equals(tableReservation.getDate(), reservation.getDate())
                    && Objects.equals(tableReservation.getHour(), reservation.getHour())) {
                throw new DuplicateKeyException("This table has been reserved on " + reservation.getDate()
                        + " at " + reservation.getHour());
            }
        }
        if (request.getCountPeople() > request.getTableRestaurant().getSeats()){
            throw new BadRequestException("The table you've chosen cannot fit more than " + request.getTableRestaurant().getSeats() + " people.");
        }
        TableReservation savedReservation = repository.save(tableReservation);
        return repository.save(savedReservation);
    }

    @Override
    public TableReservation updateReservation(Long id, TableReservationRequest request) {
        TableReservation tableReservation = repository.findById(id).orElseThrow();

        TableRestaurant table = request.getTableRestaurant();
        User user = request.getUser();

        if (request.getDate() != null) {
            tableReservation.setDate(request.getDate());
        }
        if (request.getHour() != null) {
            tableReservation.setHour(request.getHour());
        }
        if (request.getCountPeople() != null) {
            tableReservation.setCountPeople(request.getCountPeople());
        }
        if (request.getTotalPrice() != null) {
            tableReservation.setTotalPrice(request.getTotalPrice());
        }
        if (table != null) {
            tableReservation.setTableRestaurant(table);
        }
        if (user != null) {
            tableReservation.setUser(user);
        }
        if (request.getPaymentStatus() != null) {
            tableReservation.setPaymentStatus(request.getPaymentStatus());
        }
        return repository.save(tableReservation);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
