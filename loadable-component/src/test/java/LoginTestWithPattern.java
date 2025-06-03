import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.Test;
import ru.nmt.pages.HomePageWithPattern;
import ru.nmt.pages.LoginPageWithPattern;

public class LoginTestWithPattern {

    @Test
    void successfulLoginTest() {
        HomePageWithPattern homePage = Selenide.page(LoginPageWithPattern.class)
                                               .open()
                                               .login("user123", "password");
        homePage.open();
    }
}
