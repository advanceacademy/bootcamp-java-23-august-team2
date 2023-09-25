package com.aacademy.moonlight.controller.contactForm;

import com.aacademy.moonlight.dto.contactForm.ContactFormRequest;
import com.aacademy.moonlight.dto.contactForm.ContactFormResponse;
import com.aacademy.moonlight.service.contactForm.ContactFormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1/contact-form")
public class ContactFormController {
    @Autowired
    ContactFormService contactFormService;

    @PostMapping(path = "/create-form")
    public ResponseEntity<String> createContactForm(@RequestBody ContactFormRequest request){
        ContactFormResponse contactFormResponse = contactFormService.createContactForm(request);
        String response = String.format("Contact form created from:%n %s%n %s%n %s%n", contactFormResponse.getUserName(),
                contactFormResponse.getUserEmail(),contactFormResponse.getUserPhoneNumber());
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
