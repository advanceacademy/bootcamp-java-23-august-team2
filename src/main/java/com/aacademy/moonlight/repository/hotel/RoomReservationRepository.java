package com.aacademy.moonlight.repository.hotel;

import com.aacademy.moonlight.entity.hotel.RoomReservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomReservationRepository extends JpaRepository<RoomReservation, Long> {
}
