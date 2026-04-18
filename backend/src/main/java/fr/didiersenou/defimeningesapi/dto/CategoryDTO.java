package fr.didiersenou.defimeningesapi.dto;

import java.util.UUID;

public record CategoryDTO(
        UUID id,
        String locale,
        String name,
        Integer defaultTimeLimit) {
}