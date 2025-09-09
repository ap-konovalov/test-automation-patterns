package ru.nmt.dto;

public record FirstStage(
        boolean reusable,
        int engines,
        double fuel_amount_tons,
        int burn_time_sec,
        Thrust thrust_sea_level,
        Thrust thrust_vacuum
) {

}
