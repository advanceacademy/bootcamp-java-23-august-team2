package com.aacademy.moonlight.service.restaurant;

import com.aacademy.moonlight.converter.restaurant.TableReservationConverter;
import com.aacademy.moonlight.dto.restaurant.TableReservationRequest;
import com.aacademy.moonlight.entity.restaurant.TableReservation;
import com.aacademy.moonlight.entity.restaurant.TableRestaurant;
import com.aacademy.moonlight.entity.user.User;
import com.aacademy.moonlight.repository.restaurant.TableReservationRepository;
import com.aacademy.moonlight.service.restaurant.impl.TableReservationServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TableReservationServiceTest {
    @InjectMocks
    private TableReservationServiceImpl tableReservationService;

    @Mock
    private TableReservationRepository repository;

    @Mock
    private TableReservationConverter converter;

    @BeforeEach
    void setUp() {
        TableRestaurant table = new TableRestaurant();
        TableReservation reservationToSave = TableReservation.builder()
                .date(LocalDate.now())
                .hour(LocalTime.now())
                .countPeople(4)
                .totalPrice(100.0)
                .tableRestaurant(table)
                .user(new User())
                .paymentStatus(false)
                .build();
    }
    @Test
    void testBookReservation(){
        //Arrange
        TableReservationRequest tableReservationRequest = new TableReservationRequest();
        TableReservation toBookReservation = new TableReservation();

        //Mock repository
        when(converter.toReservation(tableReservationRequest)).thenReturn(toBookReservation);
        when(repository.save(toBookReservation)).thenReturn(toBookReservation);

        //Act
        TableReservation bookedReservation = tableReservationService.bookReservation(tableReservationRequest);

        //Assert NotNull
        assertNotNull(bookedReservation.getId() == toBookReservation.getId());
    }
    @Test
    void testUpdateReservation(){
        //Arrange
        Long reservationId = 1L;
        TableReservationRequest request = new TableReservationRequest();
        request.setDate(LocalDate.now());
        request.setHour(LocalTime.now());
        request.setCountPeople(4);
        request.setTotalPrice(100.0);
        TableReservation tableReservation = new TableReservation();
        tableReservation.setId(reservationId);

        //Mock repository behavior
        when(repository.findById(reservationId)).thenReturn(Optional.of(tableReservation));
        when(repository.save(tableReservation)).thenReturn(tableReservation);

        //Act
        TableReservation updatedReservation = tableReservationService.updateReservation(reservationId, request);

        //Assert
        assertNotNull(updatedReservation);
        assertEquals(request.getDate(), updatedReservation.getDate());
        assertEquals(request.getHour(), updatedReservation.getHour());
        assertEquals(request.getCountPeople(), updatedReservation.getCountPeople());
        assertEquals(request.getTotalPrice(), updatedReservation.getTotalPrice());

        //Verify that the repository`s findById and save were called
        verify(repository, times(1)).findById(reservationId);
        verify(repository, times(1)).save(tableReservation);
    }

    @Test
    void testDeleteReservation(){
        //Arrange
        Long reservationIdToDelete = 1L;

        //Act
        tableReservationService.deleteById(reservationIdToDelete);

        //Verify
        verify(repository, times(1)).deleteById(reservationIdToDelete);
    }
}
