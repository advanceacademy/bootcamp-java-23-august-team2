package com.aacademy.moonlight.entity.hotel;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
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
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "facility",nullable = false)
    @NotEmpty(message = "Enter facility")
    private String facility;

    @ManyToMany
    @JoinTable(
            name = "room_facility_mapping",
            joinColumns = @JoinColumn(name = "room_facility_id"),
            inverseJoinColumns = @JoinColumn(name = "room_id")
    )
    private List<Room> rooms;
}
