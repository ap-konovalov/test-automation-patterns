package ru.nmt.facades;

import com.codeborne.selenide.Selenide;
import ru.nmt.pages.LoginPage;
import ru.nmt.pages.RegistrationPage;

import java.util.Random;

import static com.codeborne.selenide.Selenide.page;

public class AuthorizationFacade {

    private static final Random random = new Random();
    private String username = "Aleks" + random.nextInt();
    private String password = "aAbcd-ee" + random.nextInt();

    public void registerAndLoginUser() {
        registerUser(username, password);
        loginUser(username, password);
    }

    private void registerUser(String username, String password) {
        Selenide.open("https://bookcart.azurewebsites.net/register");
        RegistrationPage registrationPage = page(RegistrationPage.class);
        registrationPage.fillFirstNameInput("TestFirstUser")
                        .fillLastNameInput("TestLastUser")
                        .fillUserNameInput(username)
                        .fillPasswordInput(password)
                        .fillConfirmPasswordInput(password)
                        .clickMaleRadioBtn()
                        .clickRegisterBtn();
    }

    private void loginUser(String username, String password) {
        Selenide.open("https://bookcart.azurewebsites.net/login");
        LoginPage loginPage = page(LoginPage.class);
        loginPage.fillUsernameInput(username)
                 .fillPasswordInput(password)
                 .clickLoginBtn();
    }
}
