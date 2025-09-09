import org.junit.jupiter.api.Test;
import ru.nmt.client.PetStoreApiClient;
import ru.nmt.dto.AddPetRequestBody;
import ru.nmt.dto.AddPetResponse;
import ru.nmt.dto.Category;
import ru.nmt.dto.Tag;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PostPetTests {

    private static final PetStoreApiClient CLIENT = new PetStoreApiClient();

    @Test
    void addPetTest() {
//        ❌ Повторение одного и того же кода (создание животного) в разных тестах.
//        ❌ Если схема AddPetRequestBody изменится (например, добавится новое поле), нужно менять все тесты вручную.
//        ❌ Тест становится менее читаемым — в нём смешано всё: и подготовка данных, и действия, и проверки.
//        ❌ Нарушается принцип DRY (Don’t Repeat Yourself — не повторяйся).
        Category birdsCategory = new Category(1, "Birds");
        List<String> photoUrls = List.of("url1");
        List<Tag> birdTags = List.of(new Tag(1, "bird"));
        String birdName = "Chirik";
        AddPetRequestBody requestBody = new AddPetRequestBody(0, birdsCategory, birdName, photoUrls, birdTags, "NEW");

        AddPetResponse response = CLIENT.postPet(requestBody);

        assertEquals(birdName, response.name(), "Имя птицы в запросе и ответе не совпадают");
    }

}
