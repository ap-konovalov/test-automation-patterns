package ru.nmt.screenplay;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;
import static org.hamcrest.Matchers.equalTo;

public class IsRocketInfoCorrect implements Question<Boolean> {

    private String rocketId;

    private IsRocketInfoCorrect(String rocketId) {
        this.rocketId = rocketId;
    }

    public static IsRocketInfoCorrect withRocketId(String rocketId) {
        return new IsRocketInfoCorrect(rocketId);
    }

    @Override
    public Boolean answeredBy(Actor actor) {
        actor.should(
                seeThatResponse("Успешно вернулась информация о ракете", response -> {
                    response.statusCode(200).body("rocket_id", equalTo(rocketId));
                })
        );
        return true;
    }
}
