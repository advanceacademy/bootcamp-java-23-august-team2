package com.aacademy.moonlight.dto.car;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CarTransferResponse {
    private Long id;
    private LocalDate date;
    private String carBrand;
    private String carModel;
    private String userName;
    private Double price;
    private boolean status;
}
