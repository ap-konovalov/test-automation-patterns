package ru.nmt.client;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.apache.http.HttpStatus;
import ru.nmt.dto.Pet;

import static io.restassured.RestAssured.given;
import static ru.nmt.constants.Endpoints.BASE_URL;
import static ru.nmt.constants.Endpoints.GET_PET_FORMATTED;
import static ru.nmt.constants.Endpoints.POST_PET;

public class PetStoreApiClient {

    public Pet postPet(Pet requestBody) {
        return given(getSpecification())
                .body(requestBody)
                .post(POST_PET)
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .as(Pet.class);
    }

    public Pet getPet(long petId) {
        return given(getSpecification())
                .get(GET_PET_FORMATTED.formatted(petId))
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .as(Pet.class);
    }

    private RequestSpecification getSpecification() {
        return new RequestSpecBuilder()
                .setBaseUri(BASE_URL)
                .setContentType(ContentType.JSON)
                .build();
    }
}
