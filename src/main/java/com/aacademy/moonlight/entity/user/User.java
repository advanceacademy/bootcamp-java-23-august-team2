package com.aacademy.moonlight.entity.user;

import com.aacademy.moonlight.entity.bar.ScreenReservation;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Entity
@Table(name = "users")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "first_name", nullable = false)
    @NotEmpty(message = "Enter your first name")
    @Size(min = 2, max = 255)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    @NotEmpty(message = "Enter your last name")
    @Size(min = 2, max = 255)
    private String lastName;

    @Column(name = "email", unique = true, nullable = false)
    //@Size(min = 5, max = 255) ---- @Email check this
    @NotBlank(message = "Enter valid email")
    @Email
    private String email;

    @Column(name = "phone_number", nullable = false)
    //@NotBlank(message = "Enter your phone number")
    @Pattern(regexp = "^(\\+|00)[0-9\\-]{10,15}$")
    private String phoneNumber;

    @Column(name = "password", nullable = false)
    @NotEmpty(message = "Enter password")
    //@ValidPassword //Custom annotation
    //Another option for password validation
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[A-Z])(?=.*[a-z])(?=.*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?]).*$")
    private String password;

    @CreatedDate
    @Column(name = "created_date", nullable = false)
    @NotNull(message = "Enter valid date")
    @Builder.Default
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    //@JsonFormat(pattern = "dd/MM/yyyy") - if we need to serialize Date
    private LocalDateTime createdDate = LocalDateTime.now();


    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
 //   @JoinTable(name = "user_role_id")
//    joinColumns = @JoinColumn(name = "user_id"),
//    inverseJoinColumns = @JoinColumn(name = "user_role_id"))
    private UserRole role;


    @OneToMany(mappedBy = "user")
    @JsonManagedReference
    private Set<ScreenReservation> screenReservationSet;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.getUserRole()));
    }


    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
