package com.aacademy.moonlight.entity.hotel;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "room_facilities")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoomFacility {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "facility",nullable = false)
    @NotEmpty(message = "Enter facility")
    private String facility;

    @ManyToMany(mappedBy = "facilities")
    private List<Room> rooms;
}
