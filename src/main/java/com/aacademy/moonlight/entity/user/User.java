package com.aacademy.moonlight.entity.user;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Entity
@Table(name = "users")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
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
    @NotBlank(message = "Enter your phone number")
    @Pattern(regexp = "^(\\+|00)[0-9\\-]{10,15}$")
    @Size(min = 10, max = 15)
    private String phoneNumber;

    @Column(name = "password", nullable = false)
    @NotEmpty(message = "Enter password")
    @ValidPassword //Custom annotation
    //Another option for password validation
    //regexp = "^(?=.*[0-9])(?=.*[A-Z])(?=.*[a-z])(?=.*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?]).*$"
    // @Size(min = 7)
    private String password;

    @Column(name = "created_date", nullable = false)
    @NotNull(message = "Enter valid date")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    //@JsonFormat(pattern = "dd/MM/yyyy") - if we need to serialize Date
    private LocalDate createdDate;

    @ManyToOne
    @JsonManagedReference
    @JoinColumn(name = "user_role_id")
    private UserRole userRole;
}
