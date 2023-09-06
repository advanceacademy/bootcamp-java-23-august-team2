package com.aacademy.moonlight.service.restaurant;

import com.aacademy.moonlight.dto.restaurant.TableReservationRequest;
import com.aacademy.moonlight.entity.restaurant.TableReservation;
import org.springframework.stereotype.Service;

@Service
public interface TableReservationService {
    TableReservation bookReservation(TableReservationRequest request);
    TableReservation updateReservation(Long id, TableReservationRequest request);
    void deleteById(Long id);
}
