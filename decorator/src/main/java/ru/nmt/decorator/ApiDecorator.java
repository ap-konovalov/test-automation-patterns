package ru.nmt.decorator;

import ru.nmt.client.ApiClient;
import ru.nmt.dto.RocketInfoResponseDto;

public abstract class ApiDecorator implements ApiClient {

    protected ApiClient apiClient;

    public ApiDecorator(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    @Override
    public RocketInfoResponseDto getRocketInfo(String rocketId) {
        return apiClient.getRocketInfo(rocketId);
    }
}
