package hardcoded_tests;

import org.junit.jupiter.api.Test;
import ru.nmt.client.di_client.RestAssuredApiClient;
import ru.nmt.client.PetHardcodedApiClient;
import ru.nmt.client.di_client.StoreDiApiClient;
import ru.nmt.dto.Category;
import ru.nmt.dto.Order;
import ru.nmt.dto.Pet;
import ru.nmt.dto.Tag;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.nmt.constants.Endpoints.BASE_URI;

public class PostStoreOrderTests {

    private static final RestAssuredApiClient apiClient = new RestAssuredApiClient(BASE_URI);
    // У этого клиента RestAssuredApiClient создается внутри
    private static final PetHardcodedApiClient PET_CLIENT = new PetHardcodedApiClient();
    // У этого клиента RestAssuredApiClient создается снаружи и внедряется через конструктор.
    private static final StoreDiApiClient STORE_CLIENT = new StoreDiApiClient(apiClient);

    private static final String PLACED_ORDER_STATUS = "placed";

    @Test
    void postOrderTest() {
        Pet pet = PET_CLIENT.postPet(getPet());

        Order order = STORE_CLIENT.postOrder(getOrder(pet));

        assertEquals(PLACED_ORDER_STATUS, order.status());
        assertEquals(pet.id(), order.petId());
    }

    private static Order getOrder(Pet pet) {
        return new Order(1, pet.id(), 1, "2025-09-16T07:18:59.270Z", PLACED_ORDER_STATUS, true);
    }

    private static Pet getPet() {
        Category birdsCategory = new Category(1, "Birds");
        List<String> photoUrls = List.of("url1");
        List<Tag> birdTags = List.of(new Tag(1, "bird"));
        String birdName = "Chirik";
        return new Pet(0, birdsCategory, birdName, photoUrls, birdTags, "NEW");
    }
}
