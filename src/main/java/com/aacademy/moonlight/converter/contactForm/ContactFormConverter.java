package com.aacademy.moonlight.converter.contactForm;

import com.aacademy.moonlight.dto.contactForm.ContactFormRequest;
import com.aacademy.moonlight.dto.contactForm.ContactFormResponse;
import com.aacademy.moonlight.entity.contactUsForm.ContactForm;
import org.springframework.stereotype.Component;

@Component
public class ContactFormConverter {

    public ContactForm toContactForm(ContactFormRequest request){
        return ContactForm.builder()
                .userName(request.getUserName())
                .userEmail(request.getUserEmail())
                .userPhoneNumber(request.getUserPhoneNumber())
                .message(request.getMessage())
                .build();
    }

    public ContactFormResponse toResponse(ContactForm savedContactForm){
        return new ContactFormResponse(
                savedContactForm.getId(),
                savedContactForm.getUserName(),
                savedContactForm.getUserEmail(),
                savedContactForm.getUserPhoneNumber());
    }
}
