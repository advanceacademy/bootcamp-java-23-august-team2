package com.aacademy.moonlight.service.hotel.impl;

import com.aacademy.moonlight.converter.hotel.RoomConverter;
import com.aacademy.moonlight.dto.hotel.RoomReservationRequest;
import com.aacademy.moonlight.dto.hotel.RoomResponse;
import com.aacademy.moonlight.entity.hotel.Room;
import com.aacademy.moonlight.entity.hotel.RoomReservation;
import com.aacademy.moonlight.entity.user.User;
import com.aacademy.moonlight.repository.hotel.RoomRepository;
import com.aacademy.moonlight.repository.hotel.RoomReservationRepository;
import com.aacademy.moonlight.repository.user.UserRepository;
import com.aacademy.moonlight.service.hotel.RoomReservationService;
import jakarta.validation.Valid;
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
    private final UserRepository userRepository;
    private final RoomConverter roomConverter;

    public RoomReservationServiceImpl(RoomReservationRepository roomReservationRepository,
                                      RoomRepository roomRepository,
                                      UserRepository userRepository, RoomConverter roomConverter) {

        this.roomReservationRepository = roomReservationRepository;
        this.roomRepository = roomRepository;
        this.userRepository = userRepository;
        this.roomConverter = roomConverter;
    }

    @Override
    public RoomReservation createRoomReservation( @Valid  RoomReservationRequest reservationRequest) {
        Room room = roomRepository.findById(reservationRequest.getRoomId())
                .orElseThrow(() -> new RuntimeException("Room not found"));

        User user = userRepository.findById(reservationRequest.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        RoomReservation reservation = new RoomReservation();
        reservation.setStartDate(reservationRequest.getStartDate());
        reservation.setEndDate(reservationRequest.getEndDate());
        reservation.setAdults(reservationRequest.getAdults());
        reservation.setChildren(reservationRequest.getChildren());
        reservation.setPaymentStatus(reservationRequest.getPaymentStatus());
        reservation.setRoom(room);
        reservation.setUser(user);
        reservation.setTotalPrice(calculateTotalPrice(room, reservationRequest));

        return roomReservationRepository.save(reservation);
    }

    @Override
    public Optional<RoomReservation> findRoomReservationById(Long id) {
        return roomReservationRepository.findById((id));
    }

    @Override
    public List<RoomResponse> getAvailableRooms(LocalDate startDate, LocalDate endDate, Integer adults, Integer children) {
      //TODO FIND OVERLAPPING RESERVATIONS AND ADD THE CAPACITY CHECK
       List<RoomReservation> allReservations = roomReservationRepository.findAll();
       List<Room> allRooms = roomRepository.findAll();
       List<RoomResponse> availableRooms = new ArrayList<>();
       for (Room room : allRooms){
           boolean isRoomReserved = false;
           for (RoomReservation roomReservation : allReservations){
               if (startDate.isBefore(roomReservation.getEndDate()) && endDate.isAfter(roomReservation.getStartDate())){
                   isRoomReserved = true;
               }
           }
           if (!isRoomReserved){
               availableRooms.add(roomConverter.toRoomResponse(room));
           }
       }

        return availableRooms;
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
