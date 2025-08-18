package ru.nmt.pages;

import ru.nmt.pages.components.ContactForm;

public class BookHotelWithComponentPage {

    private ContactForm contactForm;

    private ContactForm getContactForm() {
        if (contactForm == null) {
            contactForm = new ContactForm();
        }
        return contactForm;
    }

    public BookHotelWithComponentPage fillContactForm(String name, String email) {
        getContactForm().fill(name, email);
        return this;
    }
}
