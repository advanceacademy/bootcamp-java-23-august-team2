package com.aacademy.moonlight.entity.user;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;


@Entity
@Table(name = "user_roles")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotBlank
    private String roleName;

    @OneToMany(mappedBy = "userRole")
    @Column(name = "user_role", unique = true, nullable = false)
    @NotBlank
    private List<User> users;
}
