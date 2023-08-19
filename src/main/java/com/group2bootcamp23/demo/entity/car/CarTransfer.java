package com.group2bootcamp23.demo.entity.car;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;


import java.time.LocalDate;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "car_transfers")

public class CarTransfer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "date")
    @Future(message = "The transfer can not be booked in the past moment")
    @NotNull
    @NotBlank
    private LocalDate date;


    @NotNull
    @NotBlank
    @Min(value = 600)
    private Double price;
    //will take this value from price_per_day from category

    @Enumerated(EnumType.STRING)
    @Column(name = "payment_status")
    @NotNull
    private PaymentStatusForCar status;


//    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    @JsonManagedReference
//    @JoinColumn(name = "user_id")
//    private User user;
 // Enable when we have User

    @OneToMany(mappedBy = "cars", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonBackReference
    @Column(name = "cars_id")
    @NotNull
    @NotBlank
    private Car car;

}
