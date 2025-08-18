import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.nmt.strategy.LoginContext;
import ru.nmt.strategy.LoginPasswordLoginStrategy;
import ru.nmt.strategy.PhoneLoginStrategy;


public class AOLoginWithStrategyTest {

    private LoginContext loginContext;

    @BeforeEach
    void setUp() {
        loginContext = new LoginContext();
        Selenide.open("https://private.auth.alfabank.ru/passport/cerberus-mini-blue/dashboard-blue/phone_auth?response_type=code&client_id=newclick-web&scope=openid%20newclick-web&redirect_uri=https%3A%2F%2Fweb.alfabank.ru%2Fopenid%2Fauthorize%2Fnewclick-web%3Fredirect_to%3Dhttps___web.alfabank.ru%2F&acr_values=phone_auth:sms&non_authorized_user=true");
    }

    @Test
    void loginByPhoneTest() {
        loginContext.setStrategy(new PhoneLoginStrategy());
        String phone = "999 888 77 66";
        String cardNumber = "4444 5555 6666 7777";
        loginContext.login(phone,cardNumber);
    }

    @Test
    void loginByLoginAndPasswordTest() {
        loginContext.setStrategy(new LoginPasswordLoginStrategy());
        loginContext.login("admin","admin");
    }
}
