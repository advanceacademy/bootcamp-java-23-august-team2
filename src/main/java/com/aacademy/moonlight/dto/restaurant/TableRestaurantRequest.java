package com.aacademy.moonlight.dto.restaurant;

import com.aacademy.moonlight.entity.restaurant.TableZone;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class TableRestaurantRequest {
    @Min(value = 1)
    @Max(value = 16)
    @NotNull(message = "Please select a table number")
    private Integer tableNumber;

    @NotNull(message = "Please select a table zone")
    private TableZone tableZone;

    @NotNull(message = "Table should have a smoking status")
    private Boolean is_Smoking;

    @Min(value = 1)
    @Max(value = 11)
    @NotNull(message = "Please select the number of seats")
    private Integer seats;

    @Min(value = 10)
    @NotNull(message = "Missing price")
    private Double price;
}
