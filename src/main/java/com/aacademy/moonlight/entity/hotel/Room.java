package com.aacademy.moonlight.entity.hotel;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;
import java.util.Set;

@Entity()
@Table(name = "rooms")
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Room {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "room_number",nullable = false)
    @Min(value = 1)
    @NotNull(message = "Room should have room number")
    private Integer roomNumber;

    @Column(name = "price",nullable = false)
    @Min(value = 220) // Need to be modified
    @NotNull(message = "Room should have price")
    private Double price;

    @Column(name = "area", nullable = false)
    @NotNull(message = "Room should have an area in square meters")
    private Integer area;

    @Column(name = "type", nullable = false)
    @Enumerated(EnumType.STRING)
    @NotNull()
    private RoomType type;

    @Column(name = "view", nullable = false)
    @Enumerated(EnumType.STRING)
    @NotNull()
    private RoomView view;

    //@Column(name = "capacity", nullable = false)
    //private Integer roomCapacity;

    @ManyToMany
    @JsonIgnore
    @JoinTable(
            name = "room_facility_mapping",
            joinColumns = @JoinColumn(name = "room_id"),
            inverseJoinColumns = @JoinColumn(name = "room_facility_id")
    )
    private List<RoomFacility> facilities;

    @Column(name = "room_reservations")
    @OneToMany(mappedBy = "room", fetch = FetchType.LAZY)
    @JsonBackReference
    private Set<RoomReservation> roomReservations;
}
