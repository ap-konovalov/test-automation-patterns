package ru.nmt.pages.components;

import com.codeborne.selenide.Container;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;

public class ContactForm implements Container {

    @FindBy(css = "[aria-label='Электронная почта']")
    private SelenideElement emailInput;

    @FindBy(xpath = ".//span[text()='Номер телефона']/ancestor::div[@role='combobox']//input[@type='tel']")
    private SelenideElement phoneInput;

    public void fill(String email, String phone) {
        emailInput.setValue(email);
        phoneInput.setValue(phone);
    }
}
