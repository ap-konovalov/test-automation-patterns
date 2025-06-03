package ru.nmt.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import ru.nmt.LoadableComponent;

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
        Selenide.open("https://private.auth.alfabank.ru/passport/cerberus-mini-blue/dashboard-blue/username?response_type=code&client_id" +
                              "=newclick" +
                              "-web&scope=openid%20newclick-web&acr_values=phone_auth :sms&non_authorized_user=true");
    }

    // ✅Явная проверка загрузки страницы/компонента.
    // ✅Централизация логики проверки состояния страницы.
    // ✅Упрощение тестов: тесты работают стабильнее и чище.
    // ✅Лучшая поддерживаемость и переиспользование кода.
    @Override
    protected void isLoaded() {
        userNameField.shouldBe(Condition.visible);
    }
}
