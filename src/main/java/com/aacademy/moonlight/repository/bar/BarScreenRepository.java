package com.aacademy.moonlight.repository.bar;
import com.aacademy.moonlight.entity.bar.BarScreen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface BarScreenRepository extends JpaRepository <BarScreen,Long> {
}
