import io.restassured.RestAssured;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.nmt.dto.RocketInfoResponseDto;

import java.util.function.Supplier;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.nmt.utils.RetryUtils.retry;

public class GetRocketTest {

    private static final String ROCKET_ID = "falcon9";
    private static final String GET_FALCON_9_ROCKET_INFO_URL = "rockets/%s".formatted(ROCKET_ID);

    @BeforeAll
    static void setUp() {
        RestAssured.baseURI = "https://api.spacexdata.com/v3/";
    }

    @Test
    void getRocketInfo() throws InterruptedException {
//        ❌ Повторение логики логирования в каждом тесте.
//        ❌ Если нужно изменить формат логов — придется менять везде
//        ❌ Тесты становятся «шумными» — много лишнего кода, не относящегося к сути теста.
        System.out.println("[INFO] Отправлен GET запрос по адресу: %s%s".formatted(baseURI, GET_FALCON_9_ROCKET_INFO_URL));

        // Делаем запрос с retry в случае возникновения AssertionError
        // ❌ Тесты становятся «шумными» — добавился лишний код, не относящийся к сути теста.
        // ❌ Если что-но нужно изменить в сигнатуре метода retry - придется обновлять все тесты
        RocketInfoResponseDto response = retry(() -> given()
                                                       .get(GET_FALCON_9_ROCKET_INFO_URL)
                                                       .then()
                                                       .statusCode(HttpStatus.SC_OK)
                                                       .extract()
                                                       .as(RocketInfoResponseDto.class),
                                               AssertionError.class);

        System.out.println("[INFO] Получен ответ на запрос: %s".formatted(response));

        assertEquals(ROCKET_ID, response.rocket_id(), "RocketId в запросе и ответе не совпадают");
    }
}
