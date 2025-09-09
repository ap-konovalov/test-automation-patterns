package ru.nmt.dto;

public record SecondStage(
        int engines,
        double fuel_amount_tons,
        int burn_time_sec,
        Thrust thrust,
        SecondStagePayloads payloads
) {

}
