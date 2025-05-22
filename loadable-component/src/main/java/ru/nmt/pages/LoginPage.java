package ru.nmt.pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;

import static com.codeborne.selenide.Selenide.page;

public class LoginPage {

    @FindBy(css = "[aria-label='Логин']")
    private SelenideElement userNameField;

    @FindBy(css = "[aria-label='Пароль']")
    private SelenideElement passwordField;

    @FindBy(css = "[data-test-id='login-auth-button']")
    private SelenideElement loginButton;

    public HomePage login(String username, String password) {
        userNameField.setValue(username);
        passwordField.setValue(password);
        loginButton.click();
        return page(HomePage.class);
    }
}
