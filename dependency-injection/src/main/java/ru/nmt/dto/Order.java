package ru.nmt.dto;

public record Order(
        int id,
        long petId,
        int quantity,
        String shipDate,
        String status,
        boolean complete
) {

}
