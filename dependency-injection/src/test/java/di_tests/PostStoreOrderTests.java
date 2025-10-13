package di_tests;

import org.junit.jupiter.api.Test;
import ru.nmt.client.di_client.PetDiApiClient;
import ru.nmt.client.di_client.StoreDiApiClient;
import ru.nmt.dto.Category;
import ru.nmt.dto.Order;
import ru.nmt.dto.Pet;
import ru.nmt.dto.Tag;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PostStoreOrderTests extends BaseTest {

    private static final PetDiApiClient PET_API_CLIENT = container.getComponent(PetDiApiClient.class);
    private static final StoreDiApiClient STORE_API_CLIENT = container.getComponent(StoreDiApiClient.class);
    private static final String PLACED_ORDER_STATUS = "placed";

    @Test
    void postOrderTest() {
        Pet pet = PET_API_CLIENT.postPet(getPet());

        Order order = STORE_API_CLIENT.postOrder(getOrder(pet));

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
