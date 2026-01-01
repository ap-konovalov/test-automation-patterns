package ru.nmt.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

record Engines(
        int number,
        String type,
        String version,
        String layout,
        ISP isp,
        @JsonProperty("engine_loss_max")
        int engineLossMax,
        @JsonProperty("propellant_1")
        String propellant1,
        @JsonProperty("propellant_2")
        String propellant2,
        @JsonProperty("thrust_sea_level")
        Thrust thrustSeaLevel,
        @JsonProperty("thrust_vacuum")
        Thrust thrustVacuum,
        @JsonProperty("thrust_to_weight")
        BigDecimal thrustToWeight
) {}
