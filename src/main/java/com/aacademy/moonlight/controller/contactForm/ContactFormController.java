package com.aacademy.moonlight.controller.contactForm;

import com.aacademy.moonlight.dto.contactForm.ContactFormRequest;
import com.aacademy.moonlight.service.contactForm.ContactFormService;
import jakarta.validation.Valid;
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

    @PostMapping(path = "/create")
    public ResponseEntity<String> createContactForm(@Valid @RequestBody ContactFormRequest request){
        contactFormService.createContactForm(request);
        return ResponseEntity.status(HttpStatus.CREATED).body("Thank you for reaching out to Moonlight Hotel. " +
                "Your message has been successfully sent to our team. " +
                "We appreciate your interest in our hotel.");
    }

}
