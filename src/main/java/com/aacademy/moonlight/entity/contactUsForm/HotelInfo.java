package com.aacademy.moonlight.entity.contactUsForm;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "hotel_name")
@NoArgsConstructor
@AllArgsConstructor
public class HotelInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "hotel_name", nullable = false)
    @Size(min = 2, max = 255)
    private String hotelName;

    @Column(name = "city", nullable = false)
    @Size(min = 2, max = 64)
    private String city;

    @Column(name = "address", nullable = false)
    @Size(min = 2, max = 255)
    private String address;

    @Column(name = "country", nullable = false)
    @Size(min = 2, max = 64)
    private String country;

    @Column(name = "email", nullable = false)
    @Size(min = 2, max = 255)
    @Email
    private String email;

    @Column(name = "phone_number", nullable = false)
    @NotBlank(message = "Enter your phone number")
    @Pattern(regexp = "^(\\+|00)[0-9\\-]{10,15}$")
    @Size(min = 10, max = 15)
    private String phoneNumber;


}
