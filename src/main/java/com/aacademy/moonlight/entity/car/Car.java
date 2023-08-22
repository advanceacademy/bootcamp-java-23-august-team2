package com.aacademy.moonlight.entity.car;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;


@Entity
@Table(name = "cars")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "brand")
    @NotNull(message = "The car should have brand")
    @NotBlank
    private String brand;

    @Column(name = "model")
    @NotNull(message = "The car should have model")
    @NotBlank
    private String model;

    @Column(name = "year")
    @NotNull
    @Min(value = 2015)
    private Integer manufacturingYear;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "car_categories")
    @JsonManagedReference
    private CarCategory carCategory;

    @OneToMany(mappedBy = "car", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonBackReference
    private List<FileResourcesForCar> fileResourcesForCar;
    // I am wondering if it is good to be used this: property orphanRemoval = true - if Admin decides to delete some car

    @NotNull
    @OneToMany(fetch = FetchType.LAZY)
    @JsonBackReference
    private List<CarTransfer> carTransfer;
}
