package com.aacademy.moonlight.entity.bar;


import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "screen_events")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class ScreenEvent {

    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "event")
    @NotEmpty(message = "Enter event name")
    private String event;

    @Column(name = "event_date")
    @NotNull(message = "Enter date")
    @Future
    private LocalDate date;

    @ManyToOne
    private BarScreen barScreen;
}
