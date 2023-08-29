package com.aacademy.moonlight.repository.car;

import com.aacademy.moonlight.entity.car.CarTransfer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarTransferRepository extends JpaRepository<CarTransfer, Long> {
}
