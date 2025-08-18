package ru.nmt.pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage {

    @FindBy(css = "[data-test-id='phoneInput']")
    private SelenideElement phoneInput;

    @FindBy(css = "button.phone-auth-browser__submit-button")
    private SelenideElement submitByPhoneBtn;

    @FindBy(css = "[data-test-id='login-auth-button']")
    private SelenideElement loginAuthBtn;

    // ❌ Логика всех способов логина смешана в одном методе.
    // ❌ При добавлении нового способа логина нужно менять существующий код (нарушение принципа открытости/закрытости — Open/Closed
    // Principle).
    // ❌ Много if-else, которые сложно читать и поддерживать.
    // ❌ Трудно переиспользовать код в других тестах.
    // ❌ Тесты становятся менее читаемыми.
    public void login(String authMethod, String userId, String password) {
        if (authMethod.equalsIgnoreCase("byPhone")) {
            phoneInput.setValue(userId);
            submitByPhoneBtn.click();
            // Далее идут следующие шаги для авторизации
        }

        if (authMethod.equalsIgnoreCase("byLogin")) {
            loginAuthBtn.click();
            // Далее идут следующие шаги для авторизации
        }
    }
}
