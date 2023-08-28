package com.aacademy.moonlight.repository.restaurant;

import com.aacademy.moonlight.entity.restaurant.TableReservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TableReservationRepository extends JpaRepository<TableReservation, Long> {
}
