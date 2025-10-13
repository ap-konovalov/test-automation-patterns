package ru.nmt.pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.page;

public class RegistrationPage {

    private SelenideElement firstNameInput = $x("//input[@placeholder='First name']");
    private SelenideElement lastNameInput = $x("//input[@placeholder='Last Name']");
    private SelenideElement userNameInput = $x("//input[@placeholder='User name']");
    private SelenideElement passwordInput = $x("//input[@placeholder='Password']");
    private SelenideElement confirmPasswordInput = $x("//input[@placeholder='Confirm Password']");
    private SelenideElement maleRadioButton = $x("//input[@value='Male']");
    private SelenideElement registerButton = $x("//button[.//span[text()='Register']]");

    public RegistrationPage fillFirstNameInput(String firstName) {
        firstNameInput.setValue(firstName);
        return this;
    }

    public RegistrationPage fillLastNameInput(String lastName) {
        lastNameInput.setValue(lastName);
        return this;
    }

    public RegistrationPage fillUserNameInput(String userName) {
        userNameInput.setValue(userName);
        return this;
    }

    public RegistrationPage fillPasswordInput(String password) {
        passwordInput.setValue(password);
        return this;
    }

    public RegistrationPage fillConfirmPasswordInput(String password) {
        confirmPasswordInput.setValue(password);
        return this;
    }

    public RegistrationPage clickMaleRadioBtn() {
        maleRadioButton.click();
        return this;
    }

    public LoginPage clickRegisterBtn() {
        Selenide.sleep(3000);
        registerButton.click();
        return page(LoginPage.class);
    }
}
