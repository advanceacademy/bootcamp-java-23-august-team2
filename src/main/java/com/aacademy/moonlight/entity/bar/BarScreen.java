package com.aacademy.moonlight.entity.bar;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

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
    @NotEmpty (message = "Please enter bar zone")
    private BarZone barZone;



}
