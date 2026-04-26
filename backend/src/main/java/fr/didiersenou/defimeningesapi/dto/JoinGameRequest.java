package fr.didiersenou.defimeningesapi.dto;

import jakarta.validation.constraints.NotBlank;

public record JoinGameRequest(
        @NotBlank(message = "Le code de la partie est obligatoire") String gameCode) {
}
