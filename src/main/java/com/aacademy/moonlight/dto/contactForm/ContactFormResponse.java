package com.aacademy.moonlight.dto.contactForm;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class ContactFormResponse {
    private Long id;
    private String userName;
    private String userEmail;
    private String userPhoneNumber;
}
