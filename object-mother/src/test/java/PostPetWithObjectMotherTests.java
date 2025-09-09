import org.junit.jupiter.api.Test;
import ru.nmt.client.PetStoreApiClient;
import ru.nmt.dto.AddPetResponse;
import ru.nmt.providers.PetRequestProvider;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PostPetWithObjectMotherTests {

    private static final PetStoreApiClient CLIENT = new PetStoreApiClient();

    @Test
    void addPetTest() {
        String birdName = "Chirik";

//     ✅ Тест стал короче и легче читаются.
        AddPetResponse response = CLIENT.postPet(PetRequestProvider.createDefaultAddPetRequest(birdName));

        assertEquals(birdName, response.name(), "Имя птицы в запросе и ответе не совпадают");
    }

}
