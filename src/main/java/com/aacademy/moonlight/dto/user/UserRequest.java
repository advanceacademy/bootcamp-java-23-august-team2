package com.aacademy.moonlight.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import org.hibernate.validator.constraints.Length;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class UserRequest {
    @NotBlank
    @Length(min=3, message = "Name should contain at least 3 characters")
    private String firstName;

    @NotBlank
    @Length(min=3, message = "Last name should contain at least 3 characters")
    private String lastName;

    @NotEmpty
    private String phoneNumber;

    @NotEmpty
    private String password;

    @NotBlank
    @Email(message = "Enter valid email")
    private String email;
}
