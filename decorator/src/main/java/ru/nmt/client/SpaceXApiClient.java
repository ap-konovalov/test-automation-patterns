package ru.nmt.client;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.apache.http.HttpStatus;
import ru.nmt.dto.RocketInfoResponseDto;

import static io.restassured.RestAssured.given;
import static ru.nmt.constants.Endpoints.BASE_URL;
import static ru.nmt.constants.Endpoints.GET_ROCKETS_PATH_FORMATTED;

public class SpaceXApiClient implements ApiClient {

    public RocketInfoResponseDto getRocketInfo(String rocketId) {
        return given(getSpecification())
                .get(GET_ROCKETS_PATH_FORMATTED.formatted(rocketId))
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .as(RocketInfoResponseDto.class);
    }

    private RequestSpecification getSpecification() {
        return new RequestSpecBuilder()
                .setBaseUri(BASE_URL)
                .setContentType(ContentType.JSON)
                .build();
    }
}
