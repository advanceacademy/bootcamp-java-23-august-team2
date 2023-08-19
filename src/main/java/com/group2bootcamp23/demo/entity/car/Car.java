package com.group2bootcamp23.demo.entity.car;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
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
    @Column(name = "id")
    private Long id;

    @Column(name = "brand_and_model")
    @NotNull
    @NotBlank(message = "The car should have brand and model")
    private String model;

    @Column(name = "year")
    @NotNull
    private Long manufacturingYear;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "car_categories")
    @JsonManagedReference
    @NotNull
    private List<CarCategory> carCategory;

    @OneToMany(mappedBy = "cars", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonBackReference
    private List<FileResourcesForCar> fileResourcesForCar;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cars")
    @JsonManagedReference
    @NotNull
    private List<CarTransfer> carTransfer;
}
