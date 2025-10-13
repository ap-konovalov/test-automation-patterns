import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.nmt.pages.LoginPage;
import ru.nmt.pages.RegistrationPage;

import java.util.Random;

public class LoginTest {

    private static final Random RANDOM = new Random();
    private String username;
    private String password;

    @BeforeEach
    void setUp() {
        username = "Aleks" + RANDOM.nextInt();
        password = "aAbcd-ee" + RANDOM.nextInt();
    }

    // ❌ Тест перегружается деталями выполнения простых шагов: регистрация и авторизация пользователя
    // ❌ Читаемость плохая
    // ❌ Сложно переиспользовать в других тестах шаги по регистрации/авторизации
    @Test
    void loginTest() {
        Selenide.open("https://bookcart.azurewebsites.net/register");

        LoginPage loginPage = Selenide.page(RegistrationPage.class)
                                      .fillFirstNameInput("Aleks")
                                      .fillLastNameInput("A")
                                      .fillUserNameInput(username)
                                      .fillPasswordInput(password)
                                      .fillConfirmPasswordInput(password)
                                      .clickMaleRadioBtn()
                                      .clickRegisterBtn();

        loginPage.fillUsernameInput(username)
                 .fillPasswordInput(password)
                 .clickLoginBtn();

        // Some asserts or actions
    }
}
