package com.aacademy.moonlight.dto.user;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import org.hibernate.validator.constraints.Length;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UserUpDate {
    @NotBlank
    @Length(min=3)
    private String firstName;

    @NotBlank
    @Length(min=3)
    private String lastName;

    @Email
    private String email;

    @NotEmpty
    private String phone;
}
