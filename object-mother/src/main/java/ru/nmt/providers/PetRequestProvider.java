package ru.nmt.providers;

import lombok.experimental.UtilityClass;
import ru.nmt.dto.AddPetRequestBody;
import ru.nmt.dto.Category;
import ru.nmt.dto.Tag;

import java.util.List;

//       ✅ Теперь вся логика по созданию данных для запроса вынесена в PetRequestProvider.
//       Если нужно изменить тестовые данные — правим только в одном месте.
//       ✅ Централизация: все тестовые данные в одном месте.
//       ✅ Масштабируемость: легко добавлять новые типы запросов, если они нужны
//       ✅ Удобство поддержки: при изменении модели AddPetRequestBody правим только Object Mother, а не все тесты.
@UtilityClass
public class PetRequestProvider {

    //        ✅ Повторное использование: один и тот же объект можно использовать в десятках тестов.
    public static AddPetRequestBody createDefaultAddPetRequest(String petName) {
        return createDefaultAddPetRequest(petName, "NEW");
    }

    public static AddPetRequestBody createDefaultAddPetRequest(String petName, String status) {
        return new AddPetRequestBody(0, getCategory(), petName, getPhotoUrls(), getTags(), status);
    }

    private static List<Tag> getTags() {
        return List.of(new Tag(1, "bird"));
    }

    private static List<String> getPhotoUrls() {
        return List.of("url1");
    }

    private static Category getCategory() {
        return new Category(1, "Birds");
    }
}
