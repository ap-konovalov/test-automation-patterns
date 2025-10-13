package ru.nmt.client;

import ru.nmt.client.di_client.ApiClient;
import ru.nmt.client.di_client.RestAssuredApiClient;
import ru.nmt.dto.Pet;

import static ru.nmt.constants.Endpoints.BASE_URI;
import static ru.nmt.constants.Endpoints.POST_PET;

public class PetHardcodedApiClient {

    private static int counter = 0;
    // ❌ Если такой код будет в микросервисе - его сложно будет тестировать. Так как нам может понадобиться заменить реализацию
    // RestAssuredApiClient на Mock.
    // ❌ Если захотим использовать другую реализацию ApiClient - нужно будет менять ее во всех классах, где мы прописали эту зависимость.
    private ApiClient apiClient = new RestAssuredApiClient(BASE_URI);

    public PetHardcodedApiClient() {
        System.out.println("Количество созданных экземпляров PetApiClient: " + ++counter);
    }

    public Pet postPet(Pet requestBody) {
        return apiClient.post(POST_PET, requestBody, Pet.class);
    }
}
