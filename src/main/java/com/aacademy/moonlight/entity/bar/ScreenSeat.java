package com.aacademy.moonlight.entity.bar;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Entity
@Table(name = "screen_seats")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ScreenSeat {

    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "position" , unique = true)
    @NotEmpty(message = "Position is required")
    private String position;

    @ManyToOne
    @JsonBackReference
    private BarScreen barScreen;
}
