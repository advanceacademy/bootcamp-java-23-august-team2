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
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "date",nullable = false)
    @NotNull
    @Future(message = "The transfer can not be booked in the past moment")
    private LocalDate date;

    @Column(name = "price",nullable = false)
    @NotNull
    @Min(value = 600)
    private Double price;
    //will take this value from price_per_day from category

    @Column(name = "payment_status")
    private boolean status;

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonManagedReference
    @JoinColumn(name = "user_id", nullable = false)
    private User user;


    @ManyToOne(fetch = FetchType.EAGER)
    @JsonManagedReference
    @JoinColumn(name = "car_id", nullable = false)
    private Car car;

}
