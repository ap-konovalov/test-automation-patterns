package ru.nmt.client.di_client;

import ru.nmt.dto.Pet;

import static ru.nmt.constants.Endpoints.POST_PET;

public class PetDiApiClient {

    private static int counter = 0;
    private static ApiClient apiClient;

    public PetDiApiClient(ApiClient apiClient) {
        this.apiClient = apiClient;
        System.out.println("Количество созданных экземпляров PetApiClient: " + ++counter);
    }

    public Pet postPet(Pet requestBody) {
        return apiClient.post(POST_PET, requestBody, Pet.class);
    }
}
