package ru.nmt.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
record Stage(
        boolean reusable,
        int engines,
        @JsonProperty("fuel_amount_tons")
        int fuelAmountTons,
        @JsonProperty("burn_time_sec")
        int burnTimeSec,
        @JsonProperty("thrust_sea_level")
        Thrust thrustSeaLevel,
        @JsonProperty("thrust_vacuum")
        Thrust thrustVacuum,
        Payloads payloads
) {}
