package ru.nmt.client;

import ru.nmt.dto.RocketInfoResponseDto;

public interface ApiClient {

    RocketInfoResponseDto getRocketInfo(String rocketId);
}
