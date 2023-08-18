package com.aacademy.moonlight.entity.User;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    @Size(min = 5, max = 255)
    @NotBlank(message = "Enter valid email")
    private String email;
    @Column(name = "phone_number")
    @Size(max = 15)
    @Pattern(regexp = "(\\+\\00)[- +][0-9]{15}")
    @NotBlank(message = "Enter your phone number")
    private String phoneNumber;
    @NotBlank(message = "Enter password")
    @ValidPassword
    private String password;
    @Column(name = "created_date")
    private Date createdDate;
    @ManyToOne
    @JsonManagedReference
    private UserRole userRole;
}
