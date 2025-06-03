package login;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;

import com.codeborne.selenide.Selenide;

import static com.codeborne.selenide.Selenide.$;

public class LoginTest {

    @Test
    void loginTest() {
        Selenide.open("https://private.auth.alfabank.ru/passport/cerberus-mini-blue/dashboard-blue/username?response_type=code&client_id=newclick-web&scope=openid%20newclick-web&acr_values=phone_auth :sms&non_authorized_user=true");

        // ❌ Эти селекторы нужно будет дублировать в каждом тесте
        // ❌ Если селектор поменялся - нужно заменить его во всех тестах
        // ❌ Если изменился способ логина - все 3 шага нужно менять во всех тестах
        $("[aria-label='Логин']").setValue("testuser");
        $("[aria-label='Пароль']").setValue("password123");
        $("[data-test-id='login-auth-button']").click();

        $("[data-test-id='main-title']").shouldHave(Condition.text("Добрый день"));
    }
}
