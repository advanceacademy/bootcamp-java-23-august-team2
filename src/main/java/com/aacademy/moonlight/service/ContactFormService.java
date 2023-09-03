package com.aacademy.moonlight.service;

import com.aacademy.moonlight.dto.contactForm.ContactFormRequest;
import com.aacademy.moonlight.dto.contactForm.ContactFormResponse;
import org.springframework.stereotype.Service;

@Service
public interface ContactFormService {
    ContactFormResponse createContactForm(ContactFormRequest request);
    ContactFormResponse findContactFormById(Long id);
    ContactFormResponse updateContactForm(ContactFormRequest request, Long id);
    void deleteContactFormById(Long id);

}
