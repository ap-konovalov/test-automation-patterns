import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.Test;
import ru.nmt.pages.HomePageWithPattern;
import ru.nmt.pages.LoginPageWithPattern;

import static com.codeborne.selenide.Selenide.open;

public class LoginTestWithPattern {

    @Test
    void successfulLoginTest() {
        open("https://private.auth.alfabank.ru/passport/cerberus-mini-blue/dashboard-blue/username?response_type=code&client_id=newclick" +
                     "-web&scope=openid%20newclick-web&acr_values=phone_auth :sms&non_authorized_user=true");
        HomePageWithPattern homePage = Selenide.page(LoginPageWithPattern.class)
                                               .get()
                                               .login("user123", "password");
        homePage.get();
    }
}
