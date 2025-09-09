package ru.nmt.decorator;

import ru.nmt.client.ApiClient;
import ru.nmt.dto.RocketInfoResponseDto;

import static ru.nmt.constants.Endpoints.BASE_URL;
import static ru.nmt.constants.Endpoints.GET_ROCKETS_PATH_FORMATTED;

// Это класс "обертка", который оборачивает дополнительным поведением методы ApiClient
public class LogDecorator extends ApiDecorator {

    public LogDecorator(ApiClient apiClient) {
        super(apiClient);
    }

    @Override
    public RocketInfoResponseDto getRocketInfo(String rocketId) {
        // оборачиваем вызов методов класса ApiClient дополнительным поведением (логированием)
        System.out.println("[INFO] Отправлен GET запрос по адресу: %s%s".formatted(BASE_URL,
                                                                                   GET_ROCKETS_PATH_FORMATTED.formatted(rocketId)));

        RocketInfoResponseDto response = apiClient.getRocketInfo(rocketId);

        // оборачиваем вызов методов класса ApiClient дополнительным поведением (логированием)
        System.out.println("[INFO] Получен ответ на запрос: %s".formatted(response));
        return response;
    }
}
