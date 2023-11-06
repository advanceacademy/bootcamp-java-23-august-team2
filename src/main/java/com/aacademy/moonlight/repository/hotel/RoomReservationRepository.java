package com.aacademy.moonlight.repository.hotel;

import com.aacademy.moonlight.entity.hotel.RoomReservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface RoomReservationRepository extends JpaRepository<RoomReservation, Long> {

    @Query("SELECT r FROM RoomReservation r " +
            "WHERE r.room.id = :roomId " +
            "AND (r.startDate <= :endDate) " +
            "AND (r.endDate >= :startDate)")
    List<RoomReservation> findOverlappingReservations(@Param("roomId") Long roomId,
                                                      @Param("startDate") LocalDate startDate,
                                                      @Param("endDate") LocalDate endDate);

}
