package ru.nmt.dto;

import java.util.List;

public record AddPetResponse(
        long id,
        Category category,
        String name,
        List<String> photoUrls,
        List<Tag> tags,
        String status) {

}
