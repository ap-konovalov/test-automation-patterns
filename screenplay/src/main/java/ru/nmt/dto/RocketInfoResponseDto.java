package ru.nmt.dto;

import java.util.List;

public record RocketInfoResponseDto(
        int id,
        boolean active,
        int stages,
        int boosters,
        long cost_per_launch,
        int success_rate_pct,
        String first_flight,
        String country,
        String company,
        Height height,
        Diameter diameter,
        Mass mass,
        List<PayloadWeight> payload_weights,
        List<String> flickr_images,
        FirstStage first_stage,
        SecondStage second_stage,
        Engines engines,
        LandingLegs landing_legs,
        String wikipedia,
        String description,
        String rocket_id,
        String rocket_name,
        String rocket_type
) {

}
