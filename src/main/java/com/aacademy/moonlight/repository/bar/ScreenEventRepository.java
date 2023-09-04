package com.aacademy.moonlight.repository.bar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface ScreenEventRepository extends JpaRepository<ScreenSeatRepository , Long> {
}
