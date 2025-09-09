package ru.nmt.client;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.apache.http.HttpStatus;
import ru.nmt.dto.AddPetRequestBody;
import ru.nmt.dto.AddPetResponse;

import static io.restassured.RestAssured.given;
import static ru.nmt.constants.Endpoints.BASE_URL;
import static ru.nmt.constants.Endpoints.PET;

public class PetStoreApiClient {

    public AddPetResponse postPet(AddPetRequestBody requestBody) {
        return given(getSpecification())
                .body(requestBody)
                .post(PET)
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .as(AddPetResponse.class);
    }

    private RequestSpecification getSpecification() {
        return new RequestSpecBuilder()
                .setBaseUri(BASE_URL)
                .setContentType(ContentType.JSON)
                .build();
    }
}
