package ru.nmt.client.di_client;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class RestAssuredApiClient implements ApiClient {

    private final String baseUrl;
    private static int counter = 0;

    public RestAssuredApiClient(String baseUrl) {
        this.baseUrl = baseUrl;
        System.out.println("Количество созданных экземпляров RestAssuredApiClient: " + ++counter);
    }

    public <T> T post(String path, Object body, Class<T> responseType) {
        return RestAssured
                .given()
                .baseUri(baseUrl)
                .contentType(ContentType.JSON)
                .body(body)
                .when()
                .post(path)
                .then()
                .extract()
                .as(responseType);
    }
}
