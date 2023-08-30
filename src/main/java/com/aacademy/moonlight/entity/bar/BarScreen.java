package com.aacademy.moonlight.entity.bar;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.util.Set;

@Entity
@Table(name = "bar_screen")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class BarScreen {

    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (name = "bar_zone")
    @Enumerated(EnumType.STRING)
    @NotEmpty (message = "Please enter bar zone")
    private BarZone barZone;

    @OneToMany(mappedBy = "barScreen",cascade = CascadeType.ALL)
    @JsonManagedReference
    private Set<ScreenSeat> seats;
}
