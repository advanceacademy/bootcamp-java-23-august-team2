package com.aacademy.moonlight.entity.car;
import com.aacademy.moonlight.entity.user.User;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;


import java.time.LocalDate;

@Entity
@Table(name = "car_transfers")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor


public class CarTransfer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "date")
    @NotNull
    @NotBlank
    @Future(message = "The transfer can not be booked in the past moment")
    private LocalDate date;

    @NotNull
    @NotBlank
    @Min(value = 600)
    @Column(name = "price")
    private Double price;
    //will take this value from price_per_day from category


    @Column(name = "payment_status")
    @NotNull
    private boolean status;


    @ManyToOne(fetch = FetchType.LAZY)
    @JsonManagedReference
    @JoinColumn(name = "user_id")
    private User user;


    @ManyToOne(fetch = FetchType.LAZY)
    @JsonManagedReference
    @NotNull
    @NotBlank
    private Car car;

}
