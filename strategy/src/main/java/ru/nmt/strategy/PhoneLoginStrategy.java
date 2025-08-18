package ru.nmt.strategy;

import ru.nmt.interfaces.LoginStrategy;

import static com.codeborne.selenide.Selenide.$;

public class PhoneLoginStrategy implements LoginStrategy {

    @Override
    public void login(String userId, String password) {
        $("[data-test-id='phoneInput']").setValue(userId);
        $("button.phone-auth-browser__submit-button").click();
        // следующие шаги
    }
}
