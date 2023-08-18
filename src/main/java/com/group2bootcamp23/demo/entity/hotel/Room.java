package com.group2bootcamp23.demo.entity.hotel;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity(name = "rooms")
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "room_number")
    private int roomNumber;

    private double price;

    @Enumerated(EnumType.STRING)
    private RoomType type;

    @Enumerated(EnumType.STRING)
    private RoomView view;

    @ManyToMany(mappedBy = "rooms")
    private List<RoomFacility> facilities;
}
