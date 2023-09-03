package com.aacademy.moonlight.service;

import com.aacademy.moonlight.converter.contactForm.ContactFormConverter;
import com.aacademy.moonlight.dto.contactForm.ContactFormRequest;
import com.aacademy.moonlight.dto.contactForm.ContactFormResponse;
import com.aacademy.moonlight.entity.contactUsForm.ContactForm;
import com.aacademy.moonlight.repository.contactUsForm.ContactUsFormRepository;
import com.aacademy.moonlight.service.impl.ContactFormServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;


import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ContactFormServiceTest {
    @InjectMocks
    private ContactFormServiceImpl contactFormService;
    @Mock
    private ContactUsFormRepository contactUsFormRepository;
    @Mock
    private ContactFormConverter contactFormConverter;

    private AutoCloseable autoCloseable;

    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        contactFormService = new ContactFormServiceImpl(contactUsFormRepository, contactFormConverter);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void createContactForm() {
        ContactFormRequest request = new ContactFormRequest();
        request.setUserName("Plamen Ivanov");
        request.setUserEmail("plamen.ivanov@example.com");
        request.setUserPhoneNumber("+35912345678");
        request.setMessage("Hello, I'm contacting you about the test.");

        ContactForm contactForm = new ContactForm();
        contactForm.setId(1L);
        contactForm.setUserName(request.getUserName());
        contactForm.setUserEmail(request.getUserEmail());
        contactForm.setUserPhoneNumber(request.getUserPhoneNumber());
        contactForm.setMessage(request.getMessage());

        when(contactFormConverter.toContactForm(request)).thenReturn(contactForm);
        when(contactFormConverter.toResponse(contactForm)).thenReturn(new ContactFormResponse());

        when(contactUsFormRepository.save(any())).thenReturn(contactForm);

        ContactFormResponse response = contactFormService.createContactForm(request);

        assertNotNull(response);

        verify(contactFormConverter, times(1)).toContactForm(request);
        verify(contactFormConverter, times(1)).toResponse(contactForm);

        verify(contactUsFormRepository, times(1)).save(any());

    }

    @Test
    void findContactFormById() {

    }

    @Test
    void updateContactForm() {

    }

    @Test
    void deleteContactFormById() {
        doNothing().when(contactUsFormRepository).deleteById(1L);

        contactFormService.deleteContactFormById(1L);

        verify(contactUsFormRepository, times(1)).deleteById(1L);
    }
}
