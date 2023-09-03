package com.aacademy.moonlight.dto.contactForm;

import jakarta.validation.constraints.*;
import lombok.*;
import org.hibernate.validator.constraints.Length;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class ContactFormUpdate {
    @NotBlank
    @Length(min=3)
    private String userName;

    @Email
    private String userEmail;

    @NotEmpty
    @Pattern(regexp = "^(\\+|00)[0-9\\-]{10,15}$")
    @Size(min = 10, max = 15)
    private String userPhoneNumber;

    @NotBlank(message = "You have to write your comment")
    private String message;
}
