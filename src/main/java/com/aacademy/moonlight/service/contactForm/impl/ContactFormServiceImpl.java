package com.aacademy.moonlight.service.contactForm.impl;

import com.aacademy.moonlight.converter.contactForm.ContactFormConverter;
import com.aacademy.moonlight.dto.contactForm.ContactFormRequest;
import com.aacademy.moonlight.dto.contactForm.ContactFormResponse;
import com.aacademy.moonlight.entity.contactUsForm.ContactForm;
import com.aacademy.moonlight.repository.contactUsForm.ContactUsFormRepository;
import com.aacademy.moonlight.service.contactForm.ContactFormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContactFormServiceImpl implements ContactFormService {

    private final ContactUsFormRepository repository;
    private final ContactFormConverter converter;

    @Autowired
    public ContactFormServiceImpl(ContactUsFormRepository repository, ContactFormConverter converter) {
        this.repository = repository;
        this.converter = converter;
    }


    @Override
    public ContactFormResponse createContactForm(ContactFormRequest request) {
        ContactForm contactForm = converter.toContactForm(request);
        ContactForm savedContactForm = repository.save(contactForm);

        return converter.toResponse(savedContactForm);
    }

    @Override
    public ContactFormResponse findContactFormById(Long id) {
        ContactForm contactForm = repository.findById(id).orElseThrow(
                () -> new RuntimeException("Contact form with this id not found")
        );
        return converter.toResponse(contactForm);
    }

    @Override
    public ContactFormResponse updateContactForm(ContactFormRequest request, Long id) {
        ContactForm contactForm = repository.findById(id).orElseThrow(
                () -> new RuntimeException("Contact form not found"));

        contactForm.setUserName(request.getUserName());
        contactForm.setUserEmail(request.getUserEmail());
        contactForm.setUserPhoneNumber(request.getUserPhoneNumber());
        contactForm.setMessage(request.getMessage());

        ContactForm savedContactForm = repository.save(contactForm);

        return converter.toResponse(savedContactForm);
    }

    @Override
    public void deleteContactFormById(Long id) {
        repository.deleteById(id);
    }
}
