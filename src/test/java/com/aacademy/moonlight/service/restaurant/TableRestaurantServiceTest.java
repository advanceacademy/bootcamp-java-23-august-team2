package com.aacademy.moonlight.service.restaurant;

import com.aacademy.moonlight.converter.restaurant.TableRestaurantConverter;
import com.aacademy.moonlight.dto.restaurant.TableRestaurantRequest;
import com.aacademy.moonlight.entity.restaurant.TableRestaurant;
import com.aacademy.moonlight.entity.restaurant.TableZone;
import com.aacademy.moonlight.repository.restaurant.TableRestaurantRepository;
import com.aacademy.moonlight.service.restaurant.impl.TableRestaurantServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TableRestaurantServiceTest {

    @InjectMocks
    private TableRestaurantServiceImpl tableRestaurantService;
    @Mock
    private TableRestaurantRepository repository;

    @Mock
    private TableRestaurantConverter converter;

    @BeforeEach
    void setUp() {
        //MockitoAnnotations.initMocks(this);
        TableRestaurant tableToSave = TableRestaurant.builder()
                .tableNumber(6)
                .tableZone(TableZone.SALOON)
                .is_Smoking(false)
                .seats(8)
                .price(40.00)
                .build();
    }

    @Test
    void testSaveTable() {
        // Arrange
        TableRestaurantRequest request = new TableRestaurantRequest();
        TableRestaurant tableToSave = new TableRestaurant();


        when(converter.toTable(request)).thenReturn(tableToSave);
        when(repository.save(tableToSave)).thenReturn(tableToSave);

        // Act
        TableRestaurant savedTable = tableRestaurantService.saveTable(request);

        // Assert
        assertNotNull(savedTable.getTableNumber() == tableToSave.getTableNumber());
    }

    @Test
    void testFindByNumber() {
        // Arrange
        Integer tableNumberToFind = 2;
        List<TableRestaurant> tables = new ArrayList<>();
        TableRestaurant table1 = new TableRestaurant();
        table1.setTableNumber(tableNumberToFind);
        table1.setTableZone(TableZone.BAR);
        tables.add(table1);

        when(repository.findAll()).thenReturn(tables);

        // Act
        TableRestaurant foundTable = tableRestaurantService.findByNumber(tableNumberToFind,TableZone.BAR);

        // Assert
        assertNotNull(foundTable);
        assertEquals(2, foundTable.getTableNumber());
    }

    @Test
    void testUpdateTableById() {
        // Arrange
        Long tableIdToUpdate = 2L;
        TableRestaurantRequest request = new TableRestaurantRequest();
        TableRestaurant tableToUpdate = TableRestaurant.builder()
                .tableNumber(3)
                .tableZone(TableZone.SALOON)
                .is_Smoking(false)
                .seats(8)
                .price(40.00)
                .build();

        when(repository.findById(tableIdToUpdate)).thenReturn(Optional.of(tableToUpdate));

        // Act
        TableRestaurant updatedTable = tableRestaurantService.updateTableById(tableIdToUpdate, request);

        // Assert
        assertNotNull(updatedTable);
        // Add more assertions as needed
    }

    @Test
    void testDeleteTableById() {
        // Arrange
        Long tableIdToDelete = 1L;

        // Act
        tableRestaurantService.deleteTableById(tableIdToDelete);

        // Assert
        // Verify that the delete method was called with the correct table ID
        verify(repository, times(1)).deleteById(tableIdToDelete);
    }
}
