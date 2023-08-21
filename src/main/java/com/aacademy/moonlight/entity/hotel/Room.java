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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "room_number")
    @Min(value = 1)
    @NotNull(message = "Room should have room number")
    private Integer roomNumber;

    @Column(name = "price")
    @Min(value = 220)
    @NotNull(message = "Room should have price")
    private Double price;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    @NotNull()
    private RoomType type;

    @Enumerated(EnumType.STRING)
    @Column(name = "view")
    @NotNull()
    private RoomView view;

    @ManyToMany(mappedBy = "rooms")
    @Column(name = "facilities")
    @NotNull()
    private List<RoomFacility> facilities;

    @OneToMany(mappedBy = "room", fetch = FetchType.LAZY)
    @JsonBackReference
    @Column(name = "room_reservations")
    private Set<RoomReservation> roomReservations;
}
