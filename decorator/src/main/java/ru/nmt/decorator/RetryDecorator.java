package ru.nmt.decorator;

import ru.nmt.client.ApiClient;
import ru.nmt.dto.RocketInfoResponseDto;

import static ru.nmt.utils.RetryUtils.retry;

// Это класс "обертка", который оборачивает дополнительным поведением методы ApiClient
public class RetryDecorator extends ApiDecorator {

    public RetryDecorator(ApiClient apiClient) {
        super(apiClient);
    }

    @Override
    public RocketInfoResponseDto getRocketInfo(String rocketId) {
        // оборачиваем вызов методов класса ApiClient дополнительным поведением (retry)
        System.out.println("Вызов RETRY");

        RocketInfoResponseDto retry = retry(() -> apiClient.getRocketInfo(rocketId));

        System.out.println("Конец RETRY");

        return retry;
    }
}
