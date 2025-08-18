package ru.nmt.pages;

import org.openqa.selenium.support.FindBy;
import ru.nmt.pages.components.ContactForm;

public class BookAviaWithComponentPage {

    @FindBy(css = "[data-test-id='avia-book.avia-booking-page.book-info.contact-form.card']")
    private ContactForm contactForm;

    public BookAviaWithComponentPage fillContactForm(String name, String email) {
        contactForm.fill(name, email);
        return this;
    }
}
