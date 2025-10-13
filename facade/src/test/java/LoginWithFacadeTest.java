import org.junit.jupiter.api.Test;
import ru.nmt.facades.AuthorizationFacade;

public class LoginWithFacadeTest {

    @Test
    void loginRTest() {
        new AuthorizationFacade().registerAndLoginUser();

        // Some asserts or actions
    }
}
