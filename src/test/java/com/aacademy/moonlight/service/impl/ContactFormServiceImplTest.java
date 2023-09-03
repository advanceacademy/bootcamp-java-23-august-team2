package com.aacademy.moonlight.service.impl;

import com.aacademy.moonlight.converter.contactForm.ContactFormConverter;
import com.aacademy.moonlight.dto.contactForm.ContactFormRequest;
import com.aacademy.moonlight.dto.contactForm.ContactFormResponse;
import com.aacademy.moonlight.dto.contactForm.ContactFormUpdate;
import com.aacademy.moonlight.entity.contactUsForm.ContactForm;
import com.aacademy.moonlight.repository.contactUsForm.ContactUsFormRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
class ContactFormServiceImplTest {
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
        //to prevent memory leaks
        autoCloseable.close();
    }

    @Test
    void createContactForm() {
        //given
        ContactFormRequest request = ContactFormRequest.builder()
                .userName("Plamen Ivanov")
                .userEmail("plamen.ivanov@example.com")
                .userPhoneNumber("+35912345678")
                .message("Hello, I'm contacting you about the test.")
                .build();

        ContactForm contactForm = ContactForm.builder()
                .id(1L)
                .userName(request.getUserName())
                .userEmail(request.getUserEmail())
                .userPhoneNumber(request.getUserPhoneNumber())
                .message(request.getMessage())
                .build();

        when(contactFormConverter.toContactForm(request)).thenReturn(contactForm);
        when(contactUsFormRepository.save(contactForm)).thenReturn(contactForm);

        ContactFormResponse expectedResponse = new ContactFormResponse();
        when(contactFormConverter.toResponse(contactForm)).thenReturn(expectedResponse);

        //when
        ContactFormResponse response = contactFormService.createContactForm(request);

        //then
        assertNotNull(response);

        verify(contactUsFormRepository, times(1)).save(any(ContactForm.class));
        verify(contactFormConverter, times(1)).toContactForm(request);
        verify(contactFormConverter, times(1)).toResponse(contactForm);


        assertEquals(expectedResponse, response);

    }

    @Test
    void findContactFormById() {
        //given
        Long contactFormId = 1L;

        ContactForm contactForm = ContactForm.builder()
                .id(contactFormId)
                .userName("Plamen Ivanov")
                .userEmail("plamen.ivanov@example.com")
                .userPhoneNumber("+35912345678")
                .message("Hello, I'm contacting you about the test.")
                .build();

        when(contactUsFormRepository.findById(contactFormId)).thenReturn(Optional.of(contactForm));

        ContactFormResponse expectedResponse = new ContactFormResponse();
        when(contactFormConverter.toResponse(contactForm)).thenReturn(expectedResponse);

        //when
        ContactFormResponse response = contactFormService.findContactFormById(contactFormId);

        //then
        assertNotNull(response);
        verify(contactUsFormRepository, times(1)).findById(contactFormId);
        verify(contactFormConverter, times(1)).toResponse(contactForm);

        assertEquals(expectedResponse, response);
    }

    @Test
    void updateContactForm() {
        //given
        ContactFormRequest request = ContactFormRequest.builder()
                .userName("Updated Name")
                .userEmail("updated.emai@example.com")
                .userPhoneNumber("+87654321953")
                .message("Updated message")
                .build();

        Long contactFormId = 1L;
        ContactForm existingContactForm = ContactForm.builder()
                .id(contactFormId)
                .userName("Plamen Ivanov")
                .userEmail("plamen.ivanov@example.com")
                .userPhoneNumber("+35912345678")
                .message("Hello, I'm contacting you about the test.")
                .build();

        when(contactUsFormRepository.findById(contactFormId)).thenReturn(Optional.of(existingContactForm));
        when(contactUsFormRepository.save(existingContactForm)).thenReturn(existingContactForm);

        ContactFormResponse expectedResponse = new ContactFormResponse();
        when(contactFormConverter.toResponse(existingContactForm)).thenReturn(expectedResponse);

        //when
        ContactFormResponse response = contactFormService.updateContactForm(request, contactFormId);

        //then
        assertNotNull(response);

        verify(contactUsFormRepository, times(1)).findById(contactFormId);
        verify(contactUsFormRepository, times(1)).save(existingContactForm);
        verify(contactFormConverter, times(1)).toResponse(existingContactForm);
        assertEquals(expectedResponse, response);
    }

    @Test
    void deleteContactFormById() {
        Long contactFormId = 1L;

        contactFormService.deleteContactFormById(contactFormId);

        verify(contactUsFormRepository, times(1)).deleteById(contactFormId);
    }
}