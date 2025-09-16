package ru.nmt.steps;


import ru.nmt.client.PetStoreApiClient;
import ru.nmt.dto.Category;
import ru.nmt.dto.Pet;
import ru.nmt.dto.Tag;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PetSteps {

    private static final PetStoreApiClient CLIENT = new PetStoreApiClient();

    public Pet cretePet(String petName) {
        Category birdsCategory = new Category(1, "Birds");
        List<String> photoUrls = List.of("url1");
        List<Tag> birdTags = List.of(new Tag(1, "bird"));
        Pet requestBody = new Pet(0, birdsCategory, petName, photoUrls, birdTags, "NEW");

        return CLIENT.postPet(requestBody);
    }

    public Pet getPet(long petId) {
        return CLIENT.getPet(petId);
    }

    public void verifyPetData(Pet addPetResponse, Pet getPetResponse){
        assertEquals(addPetResponse.name(), getPetResponse.name(), "Имя птицы в запросе на создание и в ответе из БД не совпадают");
    }
}
