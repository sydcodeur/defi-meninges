package fr.didiersenou.defimeningesapi.dto;

import java.util.UUID;

public record PlayerDTO(
        UUID id,
        String username) {
}
