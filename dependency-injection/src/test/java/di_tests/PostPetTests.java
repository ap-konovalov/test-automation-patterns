package di_tests;

import org.junit.jupiter.api.Test;
import ru.nmt.client.di_client.PetDiApiClient;
import ru.nmt.dto.Category;
import ru.nmt.dto.Pet;
import ru.nmt.dto.Tag;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PostPetTests extends BaseTest {

    private static final PetDiApiClient PET_CLIENT = container.getComponent(PetDiApiClient.class);

    @Test
    void postPetTest() {
        String petName = "Chirik";
        Pet pet = PET_CLIENT.postPet(getPetWithName(petName));

        assertEquals(petName, pet.name());
    }

    private static Pet getPetWithName(String petName) {
        Category birdsCategory = new Category(1, "Birds");
        List<String> photoUrls = List.of("url1");
        List<Tag> birdTags = List.of(new Tag(1, "bird"));
        return new Pet(0, birdsCategory, petName, photoUrls, birdTags, "NEW");
    }
}
