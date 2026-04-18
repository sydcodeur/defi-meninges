package fr.didiersenou.defimeningesapi.dto;

import java.util.UUID;

public record GamePlayerDTO(
        UUID id,
        UUID gameId,
        UserDTO user,
        Integer score) {
}