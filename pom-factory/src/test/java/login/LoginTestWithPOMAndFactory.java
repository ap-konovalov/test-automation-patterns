package login;

import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.Test;
import ru.nmt.pages.LoginPage;

public class LoginTestWithPOMAndFactory {

    @Test
    void loginTest() {
        Selenide.open(
                "https://private.auth.alfabank.ru/passport/cerberus-mini-blue/dashboard-blue/username?response_type=code&client_id" +
                        "=newclick-web&scope=openid%20newclick-web&acr_values=phone_auth :sms&non_authorized_user=true");

        // ✅ Логика работы со страницей вынесена в отдельный класс.
        // ✅ Тест стал читабельным и понятным.
        // ✅ Можно переиспользовать методы login() и verifyUserIsLoggedIn() в других тестах.
        // ✅ Легко обновлять локаторы — достаточно поменять их в одном месте.
        Selenide.page(LoginPage.class)
                .login("user123", "password")
                .verifyUserIsLoggedIn();
    }
}
