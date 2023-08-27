package com.aacademy.moonlight.repository.hotel;

import com.aacademy.moonlight.entity.hotel.RoomReservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomReservationRepository extends JpaRepository<RoomReservation, Long> {
}
