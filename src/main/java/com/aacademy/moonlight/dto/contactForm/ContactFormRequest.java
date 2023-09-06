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

    @NotBlank
    @Size(min = 2, max = 255, message = "Name length must be between {min} and {max} characters")
    private String userName;

    @NotBlank
    @Email(message = "Email must be valid")
    private String userEmail;

    @NotBlank
    @Pattern(regexp = "^(\\+|00)[0-9\\-]{10,15}$")
    @Size(min = 10, max = 15)
    private String userPhoneNumber;

    @NotBlank(message = "You have to write your comment")
    private String message;
}
