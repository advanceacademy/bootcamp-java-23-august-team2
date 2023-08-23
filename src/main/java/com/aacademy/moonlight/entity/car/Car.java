package com.aacademy.moonlight.entity.car;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import java.util.List;


@Entity
@Table(name = "cars")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class Car {

    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "brand",nullable = false)
    @NotEmpty
    private String brand;

    @Column(name = "model", nullable = false)
    @NotEmpty
    private String model;

    @Column(name = "year", nullable = false)
    @Length(min=4, max=4)
    private Integer manufacturingYear;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "car_categories")
    @JsonManagedReference
    private CarCategory carCategory;

    @OneToMany(mappedBy = "car", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonBackReference
    private List<FileResourcesForCar> fileResourcesForCar;
    // I am wondering if it is good to be used this: property orphanRemoval = true - if Admin decides to delete some car

//    @NotNull
//    @OneToMany(fetch = FetchType.LAZY)
//    @JsonBackReference
//    private List<CarTransfer> carTransfer;
}
