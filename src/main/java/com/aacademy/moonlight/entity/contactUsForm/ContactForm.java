package com.aacademy.moonlight.entity.contactUsForm;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Entity
@Table(name = "contact_forms")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ContactForm {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_name", nullable = false)
    @NotEmpty
    @Size(min = 2, max = 255,message = "First name length must be between {min} and {max} characters")
    private String userName;

    @Column(name = "user_email", nullable = false, unique = true)
    @Email( message = "Email must be valid")
    @NotNull
    private String userEmail;

    @Column(name = "user_phone_number", nullable = false)
    @NotEmpty(message = "Enter your phone number")
    @Pattern(regexp = "^(\\+|00)[0-9\\-]{10,15}$")
    @Size(min = 10, max = 15)
    private String userPhoneNumber;

    @Column(name = "message", nullable = false)
    @NotNull
    @NotBlank(message = "You have to write your comment")
    private String message;


}