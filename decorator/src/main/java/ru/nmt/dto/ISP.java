package ru.nmt.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ISP(
        @JsonProperty("sea_level") int seaLevel,
        @JsonProperty("vacuum") int vacuum
) {}
