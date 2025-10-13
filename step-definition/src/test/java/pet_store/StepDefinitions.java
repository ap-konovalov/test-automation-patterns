package pet_store;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.apache.http.HttpStatus;
import ru.nmt.dto.Category;
import ru.nmt.dto.Pet;
import ru.nmt.dto.Tag;

import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.nmt.constants.Endpoints.BASE_URL;
import static ru.nmt.constants.Endpoints.POST_PET;

public class StepDefinitions {

    private Pet petRequest;
    private Pet petResponse;

    @Given("я имею корректные данные нового питомца")
    public void я_имею_корректные_данные_нового_питомца() {
        Category birdsCategory = new Category(1, "Birds");
        List<String> photoUrls = Arrays.asList("url1");
        List<Tag> birdTags = Arrays.asList(new Tag(1, "bird"));
        petRequest = new Pet(0, birdsCategory, "Chirik", photoUrls, birdTags, "NEW");
    }

    @When("я отправляю POST-запрос на создание питомца")
    public void я_отправляю_post_запрос_на_создание_питомца() {
        if (petRequest == null) {
            throw new IllegalArgumentException("Pet request body not created. Create it.");
        }
        petResponse = given(getSpecification())
                .body(petRequest)
                .post(POST_PET)
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .as(Pet.class);
    }

    @Then("API возвращает данные по созданному питомцу")
    public void api_возвращает_данные_по_созданному_питомцу() {
        assertEquals(petRequest.name(), petResponse.name());
    }

    private RequestSpecification getSpecification() {
        return new RequestSpecBuilder()
                .setBaseUri(BASE_URL)
                .setContentType(ContentType.JSON)
                .build();
    }
}

