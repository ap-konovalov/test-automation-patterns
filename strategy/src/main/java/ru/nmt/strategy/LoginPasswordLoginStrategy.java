package ru.nmt.strategy;

import ru.nmt.interfaces.LoginStrategy;

import static com.codeborne.selenide.Selenide.$;

public class LoginPasswordLoginStrategy implements LoginStrategy {

    @Override
    public void login(String userId, String password) {
        $("[data-test-id='login-auth-button']").click();
        // следующие шаги
    }
}
