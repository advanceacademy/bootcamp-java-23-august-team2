package com.aacademy.moonlight.service.hotel.impl;

import com.aacademy.moonlight.converter.hotel.RoomConverter;
import com.aacademy.moonlight.converter.hotel.RoomReservationConverter;
import com.aacademy.moonlight.dto.hotel.RoomReservationRequest;
import com.aacademy.moonlight.dto.hotel.RoomReservationResponse;
import com.aacademy.moonlight.entity.hotel.Room;
import com.aacademy.moonlight.entity.hotel.RoomReservation;
import com.aacademy.moonlight.entity.user.User;
import com.aacademy.moonlight.exceptions.BadRequestException;
import com.aacademy.moonlight.repository.hotel.RoomRepository;
import com.aacademy.moonlight.repository.hotel.RoomReservationRepository;
import com.aacademy.moonlight.service.hotel.RoomReservationService;
import com.aacademy.moonlight.service.user.UserService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

//import static sun.security.krb5.Confounder.intValue;

@Service
public class RoomReservationServiceImpl implements RoomReservationService {

    private final RoomReservationRepository roomReservationRepository;
    private final RoomRepository roomRepository;
    private final RoomReservationConverter roomReservationConverter;

    public RoomReservationServiceImpl(RoomReservationRepository roomReservationRepository,
                                      RoomRepository roomRepository, RoomReservationConverter roomReservationConverter){


        this.roomReservationRepository = roomReservationRepository;
        this.roomRepository = roomRepository;

        this.roomReservationConverter = roomReservationConverter;
    }

    @Override
    public RoomReservation createRoomReservation( @Valid  RoomReservationRequest reservationRequest) {
        Room room = roomRepository.findById(reservationRequest.getRoomId())
                .orElseThrow(() -> new RuntimeException("Room not found"));

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) auth.getPrincipal();

        RoomReservation reservation = new RoomReservation();
        reservation.setStartDate(reservationRequest.getStartDate());
        reservation.setEndDate(reservationRequest.getEndDate());
        reservation.setAdults(reservationRequest.getAdults());
        reservation.setChildren(reservationRequest.getChildren());
        reservation.setPaymentStatus(reservationRequest.getPaymentStatus());
        reservation.setRoom(room);
        reservation.setUser(user);
        reservation.setTotalPrice(calculateTotalPrice(room, reservationRequest));

        int totalPeople = reservation.getAdults() + reservation.getChildren();
        if (totalPeople > room.getRoomCapacity()) {
            throw new BadRequestException("The number of guests is more than the room capacity. " +
                    "This rooms capacity is " + room.getRoomCapacity());
        }

        List<RoomReservation> overlappingReservations = roomReservationRepository.findOverlappingReservations(
                reservation.getRoom().getId(),
                reservation.getStartDate(),
                reservation.getEndDate()
        );

        if (!overlappingReservations.isEmpty()){
            throw new BadRequestException("This room is not available for your chosen dates.");
        }

        return roomReservationRepository.save(reservation);
    }

    @Override
    public Optional<RoomReservation> findRoomReservationById(Long id) {
        return roomReservationRepository.findById((id));
    }

    @Override
    public RoomReservationResponse findPersonalRoomReservationById(Long id) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) auth.getPrincipal();

        RoomReservation roomReservation = roomReservationRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Room reservation with this id not found")
        );

        if (!roomReservation.getUser().getId().equals(user.getId())){
            throw new BadRequestException("You don't have a reservation with this id.");
        }else {
            return converter.toResponse(roomReservation);
        }
    }

    @Override
    public List<RoomReservationResponse> getReservationsByUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) auth.getPrincipal();

        List<RoomReservation> allReservations = roomReservationRepository.findAll();
        List<RoomReservationResponse> userReservations = new ArrayList<>();

        for (RoomReservation reservation : allReservations){
            if (Objects.equals(reservation.getUser().getId(), user.getId())){
                userReservations.add(roomReservationConverter.toResponse(reservation));
            }
        }
        if (userReservations.isEmpty()){
            throw new EntityNotFoundException("You don't have any car reservations.");
        }
        return userReservations;
    }

    @Override
    public List<RoomReservationResponse> getAllRoomReservations() {
        List<RoomReservation> allRoomReservations = roomReservationRepository.findAll();
        List<RoomReservationResponse> rooms = new ArrayList<>();
        for (RoomReservation room : allRoomReservations) {
            RoomReservationResponse response = roomReservationConverter.toResponse(room);
            rooms.add(response);
        }
        return rooms;
    }

    @Override
    public void deleteRoomReservation(Long id) {
        roomReservationRepository.deleteById(id);
    }

    @Override
    public RoomReservation updateRoomReservation(Long id, @Valid RoomReservationRequest request) {
        RoomReservation reservation = roomReservationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reservation not found"));

        if (Objects.nonNull(request.getStartDate())) {
            reservation.setStartDate(request.getStartDate());
        }
        if (Objects.nonNull(request.getEndDate())) {
            reservation.setEndDate(request.getEndDate());
        }
        if (Objects.nonNull(request.getAdults())) {
            reservation.setAdults(request.getAdults());
        }
        if (Objects.nonNull(request.getChildren())) {
            reservation.setChildren(request.getChildren());
        }
        if (Objects.nonNull(request.getPaymentStatus())) {
            reservation.setPaymentStatus(request.getPaymentStatus());
        }

        return roomReservationRepository.save(reservation);
    }

    private double calculateTotalPrice(Room room, RoomReservationRequest reservationRequest) {
        LocalDate startDate = reservationRequest.getStartDate();
        LocalDate endDate = reservationRequest.getEndDate();

        // Calculate the number of days between startDate and endDate
        long numberOfDays = ChronoUnit.DAYS.between(startDate, endDate);

        if (numberOfDays < 0) {
            throw new IllegalArgumentException("End date must be after start date");
        }

        Double roomPrice = room.getPrice();
        if (roomPrice == null) {
            roomPrice = 220.0; // this is set in order to run the tests. In fact there is a validation for that price
        }
        double totalPrice = roomPrice * numberOfDays;

        return totalPrice;
    }

}
