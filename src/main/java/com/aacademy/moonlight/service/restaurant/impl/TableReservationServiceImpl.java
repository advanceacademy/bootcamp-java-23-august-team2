package com.aacademy.moonlight.service.restaurant.impl;

import com.aacademy.moonlight.converter.restaurant.TableReservationConverter;
import com.aacademy.moonlight.dto.restaurant.TableReservationRequest;
import com.aacademy.moonlight.dto.restaurant.TableReservationResponse;
import com.aacademy.moonlight.dto.user.UserResponse;
import com.aacademy.moonlight.dto.restaurant.TableReservationResponse;
import com.aacademy.moonlight.entity.restaurant.TableReservation;
import com.aacademy.moonlight.entity.restaurant.TableRestaurant;
import com.aacademy.moonlight.entity.user.User;
import com.aacademy.moonlight.exceptions.AuthorizationException;
import com.aacademy.moonlight.exceptions.BadRequestException;
import com.aacademy.moonlight.repository.restaurant.TableReservationRepository;
import com.aacademy.moonlight.service.restaurant.TableReservationService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
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
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth.getPrincipal() == null){
            throw new AuthorizationException("YOu must be logged in to make a reservation.");
        }
        request.setUser((User) auth.getPrincipal());

        TableReservation tableReservation = converter.toReservation(request);
        List<TableReservation> reservations = repository.findAll();
        LocalTime currentTime = LocalTime.now();

        if (request.getHour().isBefore(currentTime) && (tableReservation.getDate().isBefore(LocalDate.now()) || tableReservation.getDate().isEqual(LocalDate.now()))){
            throw new BadRequestException("The time must be in the future");
        }

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

    @Override
    public List<TableReservationResponse> getTableReservationsByUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) auth.getPrincipal();

        List<TableReservation> allReservations = repository.findAll();
        List<TableReservationResponse> userReservations = new ArrayList<>();

        for (TableReservation reservation : allReservations){
            if (Objects.equals(reservation.getUser().getId(), user.getId())){
                userReservations.add(converter.toTableReservationResponse(reservation));
            }
        }

        if (userReservations.isEmpty()){
            throw new EntityNotFoundException("You don't have any table reservations.");
        }
        return userReservations;
    }

    @Override
    public TableReservationResponse getPersonalTableReservationById(Long id) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) auth.getPrincipal();

        TableReservation reservation = repository.findById(id).orElseThrow(
<<<<<<< HEAD
                () -> new EntityNotFoundException("Table reservation with this id not found")
=======
                 () -> new EntityNotFoundException("Table reservation with this id not found")
>>>>>>> 05a2bb4bf581921c893e50f3300703af76098337
        );

        if (!reservation.getUser().getId().equals(user.getId())){
            throw new BadRequestException("You don't have a reservation with this id.");
        } else {
            return converter.toTableReservationResponse(reservation);
        }
    }
}
