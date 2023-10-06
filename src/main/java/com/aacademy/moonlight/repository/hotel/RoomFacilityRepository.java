package com.aacademy.moonlight.repository.hotel;

import com.aacademy.moonlight.entity.hotel.RoomFacility;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomFacilityRepository extends JpaRepository<RoomFacility, Integer> {
}
