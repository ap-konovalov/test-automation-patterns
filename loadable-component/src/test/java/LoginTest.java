import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.Test;
import ru.nmt.pages.HomePage;
import ru.nmt.pages.LoginPage;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginTest {

    @Test
    void successfulLoginTest() {
        Selenide.open(
                "https://private.auth.alfabank.ru/passport/cerberus-mini-blue/dashboard-blue/username?response_type=code&client_id" +
                        "=newclick-web&scope=openid%20newclick-web&acr_values=phone_auth :sms&non_authorized_user=true");

        HomePage homePage = Selenide.page(LoginPage.class).login("user123", "password");

        // ❌ Эту проверку нужно будет дублировать везде, где хотим проверить что страница загрузилась
        assertTrue(homePage.isPageTitleDisplayed());
    }
}
