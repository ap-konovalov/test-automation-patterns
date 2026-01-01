package ru.nmt.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;
import java.util.List;

public record RocketInfoResponseDto(
        int id,
        boolean active,
        int stages,
        int boosters,
        @JsonProperty("cost_per_launch")
        long costPerLaunch,
        @JsonProperty("success_rate_pct")
        int successRatePct,
        @JsonProperty("first_flight")
        LocalDate firstFlight,
        String country,
        String company,
        Dimension height,
        Dimension diameter,
        Mass mass,
        @JsonProperty("payload_weights")
        List<PayloadWeight> payloadWeights,
        @JsonProperty("first_stage")
        Stage firstStage,
        @JsonProperty("second_stage")
        Stage secondStage,
        Engines engines,
        @JsonProperty("landing_legs")
        LandingLegs landingLegs,
        @JsonProperty("flickr_images")
        List<String> flickrImages,
        String wikipedia,
        String description,
        @JsonProperty("rocket_id")
        String rocketId,
        @JsonProperty("rocket_name")
        String rocketName,
        @JsonProperty("rocket_type")
        String rocketType
) {

}
