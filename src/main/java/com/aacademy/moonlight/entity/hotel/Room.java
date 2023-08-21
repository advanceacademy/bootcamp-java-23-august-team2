package com.aacademy.moonlight.entity.hotel;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import java.util.List;
import java.util.Set;

@Entity(name = "rooms")
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Room {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "room_number",nullable = false)
    @Min(value = 1)
    @NotNull(message = "Room should have room number")
    private Integer roomNumber;

    @Column(name = "price",nullable = false)
    @Min(value = 220) // Need to be modified
    @NotNull(message = "Room should have price")
    private Double price;

    @Column(name = "type", nullable = false)
    @Enumerated(EnumType.STRING)
    @NotNull()
    private RoomType type;

    @Column(name = "view", nullable = false)
    @Enumerated(EnumType.STRING)
    @NotNull()
    private RoomView view;

    @Column(name = "facilities", nullable = false)
    @ManyToMany(mappedBy = "rooms")
    @NotNull()
    private List<RoomFacility> facilities;

    @Column(name = "room_reservations")
    @OneToMany(mappedBy = "room", fetch = FetchType.LAZY)
    @JsonBackReference
    private Set<RoomReservation> roomReservations;
}
