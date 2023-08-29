package com.aacademy.moonlight.repository.contactUsForm;

import com.aacademy.moonlight.entity.contactUsForm.ContactForm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactUsFormRepository extends JpaRepository<ContactForm,Long> {
}
