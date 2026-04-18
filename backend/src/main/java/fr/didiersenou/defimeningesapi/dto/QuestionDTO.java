package fr.didiersenou.defimeningesapi.dto;

import java.util.List;
import java.util.UUID;

public record QuestionDTO(
        UUID id,
        CategoryDTO category,
        String locale,
        String content,
        Integer difficultyLevel,
        Integer timeLimit,
        List<AnswerDTO> answers) {
}