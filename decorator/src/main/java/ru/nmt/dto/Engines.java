package ru.nmt.dto;

public record Engines(
        int number,
        String type,
        String version,
        String layout,
        int engine_loss_max,
        String propellant_1,
        String propellant_2,
        Thrust thrust_sea_level,
        Thrust thrust_vacuum,
        double thrust_to_weight
) {

}
