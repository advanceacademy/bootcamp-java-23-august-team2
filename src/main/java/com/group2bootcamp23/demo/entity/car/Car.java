package com.group2bootcamp23.demo.entity.car;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;


@Entity(name = "cars")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "car_id")
    private Long id;

    @Column(name = "brand_and_model")
    @NotNull(message = "The car should have brand and model")
    private String model;

    @Column(name = "year")
    @NotNull
    private String manufacturingYear;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "car_categories")
    @JsonManagedReference
    @NotNull
    private List<CarCategory> carCategory;

    @OneToMany(mappedBy = "cars", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonBackReference
    private List<FileResourcesForCar> fileResourcesForCar;
    // I am wondering if it is good to be used this: property orphanRemoval = true - if Admin decides to delete some car

    @OneToMany(fetch = FetchType.LAZY)
    @JsonBackReference
    @NotNull
    private List<CarTransfer> carTransfer;
}
