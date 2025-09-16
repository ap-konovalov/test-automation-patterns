package ru.nmt.screenplay;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Get;

public class GetRocketInfo implements Task {

    private String rocketId;

    private GetRocketInfo(String rocketId) {
        this.rocketId = rocketId;
    }

    public static GetRocketInfo withRocketId(String rocketId) {
        return new GetRocketInfo(rocketId);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(Get.resource("rockets/{rocketId}")
                            .with(request -> request.pathParam("rocketId", rocketId)));
    }
}
