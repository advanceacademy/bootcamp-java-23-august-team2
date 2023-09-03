package com.aacademy.moonlight.dto.user;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.validator.constraints.Length;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class UserUpDatePassword {
    @NotBlank
    @Length(min=8)
    private String password;
}
