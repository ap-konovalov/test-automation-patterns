import org.junit.jupiter.api.Test;
import ru.nmt.client.PetStoreApiClient;
import ru.nmt.dto.Category;
import ru.nmt.dto.Pet;
import ru.nmt.dto.Tag;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PostPetTests {

    private static final PetStoreApiClient CLIENT = new PetStoreApiClient();

    @Test
    void addPetTest() {
        Category birdsCategory = new Category(1, "Birds");
        List<String> photoUrls = List.of("url1");
        List<Tag> birdTags = List.of(new Tag(1, "bird"));
        String birdName = "Chirik";
        Pet requestBody = new Pet(0, birdsCategory, birdName, photoUrls, birdTags, "NEW");

        Pet addPetResponse = CLIENT.postPet(requestBody);

        Pet getPetResponse = CLIENT.getPet(addPetResponse.id());

        assertEquals(addPetResponse.name(), getPetResponse.name(), "Имя птицы в запросе на создание и в ответе из БД не совпадают");
    }

}
