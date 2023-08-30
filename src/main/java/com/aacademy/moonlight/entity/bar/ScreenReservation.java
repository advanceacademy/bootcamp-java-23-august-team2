package com.aacademy.moonlight.entity.bar;

import com.aacademy.moonlight.entity.user.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "screen_reservation")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ScreenReservation {

    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "date")
    @NotNull (message = "Please pick a date")
    @Future
    private LocalDate date;

    @OneToOne
    @JoinColumn(name = "screen_event_id")
    private ScreenEvent screenEvent;

    @Column(name = "total_price",nullable = false)
    @NotNull
    private Double totalPrice;

    @OneToMany
    @JsonManagedReference
    private Set<ScreenSeat> screenSeat;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private User user;

    @Column(name = "payment_status")
    private boolean paymentStatus;
}
