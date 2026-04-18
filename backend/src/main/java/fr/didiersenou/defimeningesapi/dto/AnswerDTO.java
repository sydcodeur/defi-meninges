package fr.didiersenou.defimeningesapi.dto;

import java.util.UUID;

public record AnswerDTO(
        UUID id,
        UUID questionId,
        String locale,
        String content,
        boolean isCorrect) {
}