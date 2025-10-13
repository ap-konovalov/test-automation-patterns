package ru.nmt.client.di_client;

import ru.nmt.dto.Order;

import static ru.nmt.constants.Endpoints.POST_STORE_ORDER;

public class StoreDiApiClient {

    private final ApiClient apiClient;

    public StoreDiApiClient(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    public Order postOrder(Order requestBody) {
        return apiClient.post(POST_STORE_ORDER, requestBody, Order.class);
    }
}
