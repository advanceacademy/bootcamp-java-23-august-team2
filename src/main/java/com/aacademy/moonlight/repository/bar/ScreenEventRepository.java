package com.aacademy.moonlight.repository.bar;

import com.aacademy.moonlight.entity.bar.ScreenEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScreenEventRepository extends JpaRepository <ScreenEvent, Long>  {
}
