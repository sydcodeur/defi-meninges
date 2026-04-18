package fr.didiersenou.defimeningesapi.dto;

import fr.didiersenou.defimeningesapi.enums.GameMode;
import jakarta.validation.constraints.NotNull;
import java.util.UUID;

public record GameCreateRequest(
                @NotNull(message = "Le mode de jeu est obligatoire") GameMode mode,
                @NotNull(message = "L'ID du créateur est obligatoire") UUID creatorId) {
}