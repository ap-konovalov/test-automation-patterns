package ru.nmt.pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class LoginPage {

    private SelenideElement usernameInput = $x("//input[@placeholder='Username']");
    private SelenideElement passwordInput = $x("//input[@placeholder='Password']");
    private SelenideElement loginButton = $x("//button[.//span[text()='Login']]");

    public LoginPage fillUsernameInput(String username) {
        usernameInput.setValue(username);
        return this;
    }

    public LoginPage fillPasswordInput(String password) {
        passwordInput.setValue(password);
        return this;
    }

    public void clickLoginBtn() {
        Selenide.sleep(3000);
        loginButton.click();
    }
}
