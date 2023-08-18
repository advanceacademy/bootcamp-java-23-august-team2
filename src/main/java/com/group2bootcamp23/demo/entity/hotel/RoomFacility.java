package com.group2bootcamp23.demo.entity.hotel;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity(name = "room_facilities")
public class RoomFacility {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;


    private String facility;

    @ManyToMany
    @JoinTable(
            name = "room_facility_mapping",
            joinColumns = @JoinColumn(name = "room_facility_id"),
            inverseJoinColumns = @JoinColumn(name = "room_id")
    )
    private List<Room> rooms;
}
