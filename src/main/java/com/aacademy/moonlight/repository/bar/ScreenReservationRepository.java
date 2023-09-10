package com.aacademy.moonlight.repository.bar;
import com.aacademy.moonlight.entity.bar.ScreenReservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface ScreenReservationRepository extends JpaRepository <ScreenReservation,Long> {
}
