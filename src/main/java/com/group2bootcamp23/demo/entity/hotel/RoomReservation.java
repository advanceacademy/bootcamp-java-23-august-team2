package com.group2bootcamp23.demo.entity.hotel;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
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
    private long id;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @Column(name = "total_price")
    private double totalPrice;

    private  int adults;

    private int children;

    @ManyToOne
    @JsonManagedReference
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

