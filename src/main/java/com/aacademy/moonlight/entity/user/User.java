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

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "first_name")
    @Size(min = 2, max = 255)
    @NotEmpty(message = "Enter your first name")
    private String firstName;

    @Column(name = "last_name")
    @Size(min = 2, max = 255)
    @NotEmpty(message = "Enter your last name")
    private String lastName;

    @Email
    @Column(unique = true, name = "email")
    //@Size(min = 5, max = 255) ---- @Email check this
    @NotBlank(message = "Enter valid email")
    private String email;

    @Column(name = "phone_number")
    @Pattern(regexp = "^(\\+|00)[0-9\\-]{10,15}$")
    @Size(min = 10, max = 15)
    @NotBlank(message = "Enter your phone number")
    private String phoneNumber;

    @NotBlank(message = "Enter password")
    @ValidPassword //Custom annotation
    //Another option for password validation
    //regexp = "^(?=.*[0-9])(?=.*[A-Z])(?=.*[a-z])(?=.*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?]).*$"
    // @Size(min = 7)
    @Column(name = "password")
    private String password;

    @Column(name = "created_date")
    @NotNull(message = "Enter valid date")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    //@JsonFormat(pattern = "dd/MM/yyyy") - if we need to serialize Date
    private LocalDate createdDate;

    @ManyToOne
    @JsonManagedReference
    @JoinColumn(name = "user_role_id")
    private UserRole userRole;
}
