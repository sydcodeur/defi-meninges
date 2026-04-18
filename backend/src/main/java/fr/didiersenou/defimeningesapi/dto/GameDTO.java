package fr.didiersenou.defimeningesapi.dto;

import fr.didiersenou.defimeningesapi.enums.GameMode;
import fr.didiersenou.defimeningesapi.enums.GameStatus;
import java.time.LocalDateTime;
import java.util.UUID;

public record GameDTO(
        UUID id,
        GameMode mode,
        GameStatus status,
        LocalDateTime createdAt) {
}