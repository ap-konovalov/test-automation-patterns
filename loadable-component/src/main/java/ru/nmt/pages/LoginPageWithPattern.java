package ru.nmt.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.LoadableComponent;

// Наследуемся от абстрактного класса Selenium - LoadableComponent
public class LoginPageWithPattern extends LoadableComponent<LoginPageWithPattern> {

    @FindBy(css = "[aria-label='Логин']")
    private SelenideElement userNameField;

    @FindBy(css = "[aria-label='Пароль']")
    private SelenideElement passwordField;

    @FindBy(css = "[data-test-id='login-auth-button']")
    private SelenideElement loginButton;

    public HomePageWithPattern login(String username, String password) {
        userNameField.setValue(username);
        passwordField.setValue(password);
        loginButton.click();
        return new HomePageWithPattern();
    }

    @Override
    protected void load() {
    }

    @Override
    protected void isLoaded() {
        userNameField.shouldBe(Condition.visible);
    }
}
