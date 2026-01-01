import org.junit.jupiter.api.Test;
import ru.nmt.client.ApiClient;
import ru.nmt.client.SpaceXApiClient;
import ru.nmt.decorator.LogDecorator;
import ru.nmt.decorator.RetryDecorator;
import ru.nmt.dto.RocketInfoResponseDto;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GetRocketWithDecoratorTest {

    private static final ApiClient spaceXApiClientWithDecorators = createClientWithDecorators(true, true);

    @Test
    void getRocketInfo() {
        String rocketId = "falcon9";
        RocketInfoResponseDto response = spaceXApiClientWithDecorators.getRocketInfo(rocketId);
        assertEquals(rocketId, response.rocketId(), "RocketId в запросе и ответе не совпадают");
    }

    private static ApiClient createClientWithDecorators(boolean isLogDecoratorEnabled, boolean isRetryDecoratorEnabled) {
        ApiClient client = new SpaceXApiClient();
        if (isRetryDecoratorEnabled) {
            client = new RetryDecorator(client);
        }
        if (isLogDecoratorEnabled) {
            client = new LogDecorator(client);
        }
        return client;
    }
}
