package com.aacademy.moonlight.dto.contactForm;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class ContactFormRequest {

    @NotBlank(message = "Name field must not be empty")
    @Size(min = 2, max = 255, message = "Name length must be between {min} and {max} characters")
    private String userName;

    @NotBlank(message = "Email field must not be empty")
    @Email(message = "Email must be valid")
    private String userEmail;

    @NotBlank(message = "Phone number field must not be empty")
    @Pattern(regexp = "^(\\+|00)[0-9\\-]{10,15}$", message = "Please enter a valid phone number.")
    @Size(min = 10, max = 15)
    private String userPhoneNumber;

    @NotBlank(message = "Message field must not be empty")
    private String message;
}
