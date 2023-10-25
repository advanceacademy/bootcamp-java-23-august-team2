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

    @Query(nativeQuery = true,
            value =
                    "SELECT * FROM reservations " +
                            "WHERE room_id = :roomId " +
                            "AND ((start_date <= :endDate) AND (end_date >= :startDate))")
    List<RoomReservation> findOverlappingReservations(@Param("roomId") Long roomId,
                                                      @Param("startDate") LocalDate startDate,
                                                      @Param("endDate") LocalDate endDate);

}
