package com.group2bootcamp23.moonlight.entity.hotel;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import java.time.LocalDate;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "reservations")
public class RoomReservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "start_date")
    @NotNull(message = "Start date is required")
    @Future(message = "Start date must be in the future")
    private LocalDate startDate;

    @Column(name = "end_date")
    @NotNull(message = "End date is required")
    @Future(message = "End date must be in the future")
    private LocalDate endDate;

    @Column(name = "total_price")
    @Min(value = 220)
    @NotNull
    private Double totalPrice;

    @Column(name = "adults")
    @Min(value = 1, message = "Adults should be at least 1")
    @NotNull(message = "Number of adults is compulsory")
    private  Integer adults;

    @Column(name = "children")
    @Min(value = 0)
    private int children;

    @ManyToOne
    @JsonManagedReference
    @NotNull(message = "To make room reservation you need room")
    private Room room;

    /**Remains to remove the comment once the user entity is created
     * and if necessary to be rewritten*/
//    @ManyToOne()
//    @JoinColumn(name = "user_id")
//    @JsonManagedReference
//    private User user;

    @Column(name = "payment_status")
    private boolean paymentStatus;
}

