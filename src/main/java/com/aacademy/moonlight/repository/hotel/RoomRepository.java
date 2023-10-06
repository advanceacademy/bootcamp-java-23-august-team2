package com.aacademy.moonlight.repository.hotel;

import com.aacademy.moonlight.entity.hotel.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends JpaRepository<Room, Integer> {
}
