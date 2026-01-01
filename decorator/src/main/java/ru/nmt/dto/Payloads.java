package ru.nmt.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

record Payloads(
        @JsonProperty("option_1")
        String option1,
        @JsonProperty("option_2")
        String option2,
        @JsonProperty("composite_fairing")
        CompositeFairing compositeFairing) {

}
