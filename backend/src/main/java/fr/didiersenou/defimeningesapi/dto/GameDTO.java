package fr.didiersenou.defimeningesapi.dto;

import fr.didiersenou.defimeningesapi.enums.GameMode;
import fr.didiersenou.defimeningesapi.enums.GameStatus;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record GameDTO(
        UUID id,
        String gameCode,
        GameMode mode,
        GameStatus status,
        String categoryName,
        UUID hostId,
        List<GamePlayerDTO> players,
        LocalDateTime createdAt) {
}