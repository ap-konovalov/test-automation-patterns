import io.restassured.RestAssured;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.nmt.dto.RocketInfoResponseDto;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class GetRocketTest {

    private static final String ROCKET_ID = "falcon9";
    private static final String GET_FALCON_9_ROCKET_INFO_URL = "rockets/%s".formatted(ROCKET_ID);

    @BeforeAll
    static void setUp() {
        RestAssured.baseURI = "https://api.spacexdata.com/v3/";
    }

    //    ❌ Нужно знать RestAssured, чтобы читать и писать тест
    //    ❌ Суть происходящего в тесте поймет только тот, кто знает Java
    @Test
    void getRocketInfo() {
        RocketInfoResponseDto response = given()
                .get(GET_FALCON_9_ROCKET_INFO_URL)
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .as(RocketInfoResponseDto.class);

        assertEquals(ROCKET_ID, response.rocket_id(), "RocketId в запросе и ответе не совпадают");
    }
}
