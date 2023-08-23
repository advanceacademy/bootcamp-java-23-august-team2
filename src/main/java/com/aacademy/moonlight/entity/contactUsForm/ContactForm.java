package com.aacademy.moonlight.entity.contactUsForm;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Entity
@Table(name = "hotel_name")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ContactForm {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", nullable = false)
    @Size(min = 2, max = 255,message = "First name length must be between {min} and {max} characters")
    @NotNull
    private String userName;

    @NotNull
    @Column(name = "email", nullable = false, unique = true)
    @Size(min = 5, max = 255, message = "Email must be between {min} and {max} characters")
    @Email(regexp = "^[^ @]+@[^ @]+\\.[^ @]+$", message = "Email must be valid")
    private String userEmail;

    @Column(name = "phone_number", nullable = false)
    @NotBlank(message = "Enter your phone number")
    @Pattern(regexp = "^(\\+|00)[0-9\\-]{10,15}$")
    @Size(min = 10, max = 15)
    @NotNull
    private String userPhoneNumber;

    @NotNull
    @NotBlank(message = "You have to write your comment")
    private String message;


}
