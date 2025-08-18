package ru.nmt.pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;

public class BookHotelPage {

    @FindBy(css = "[aria-label='Электронная почта']")
    private SelenideElement emailInput;

    @FindBy(xpath = "//span[text()='Номер телефона']/ancestor::div[@role='combobox']//input[@type='tel']")
    private SelenideElement phoneInput;

    public BookHotelPage fillContactForm(String email, String phone) {
        emailInput.setValue(email);
        phoneInput.setValue(phone);
        return this;
    }
}
