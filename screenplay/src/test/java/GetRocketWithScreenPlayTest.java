import net.serenitybdd.core.Serenity;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;
import net.serenitybdd.screenplay.rest.interactions.Get;
import net.serenitybdd.screenplay.rest.interactions.RestInteraction;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.nmt.screenplay.GetRocketInfo;
import ru.nmt.screenplay.IsRocketInfoCorrect;

import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;
import static org.hamcrest.Matchers.equalTo;

public class GetRocketWithScreenPlayTest {

    private static final String ROCKET_ID = "falcon9";
    private static Actor sam;

    @BeforeAll
    static void setUp() {
        Serenity.throwExceptionsImmediately();
        sam = Actor.named("Ученый запускающий ракеты").whoCan(CallAnApi.at("https://api.spacexdata.com/v3/"));
    }

    @Test
    void getRocketInfo() {
        RestInteraction getRocketInfoTask = Get.resource("rockets/{rocketId}")
                                               .with(request -> request.pathParam("rocketId", ROCKET_ID));
        sam.attemptsTo(getRocketInfoTask);

        sam.should(
                seeThatResponse("Успешно вернулась информация о ракете", response -> {
                    response.statusCode(200).body("rocket_id", equalTo(ROCKET_ID));
                })
        );
    }

    // Вариант теста выше, но уже с созданием отдельных классов для Task (GetRocketInfo) и Question(IsRocketInfoCorrect)
    @Test
    void getRocketInfoSecondEdition() {
        sam.attemptsTo(GetRocketInfo.withRocketId(ROCKET_ID));

        sam.asksFor(IsRocketInfoCorrect.withRocketId(ROCKET_ID));
    }
}
