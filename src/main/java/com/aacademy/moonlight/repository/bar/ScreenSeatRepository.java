package com.aacademy.moonlight.repository.bar;
import com.aacademy.moonlight.entity.bar.ScreenSeat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface ScreenSeatRepository extends JpaRepository <ScreenSeat,Long> {
}
