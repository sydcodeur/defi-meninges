package fr.didiersenou.defimeningesapi.mapper;

import fr.didiersenou.defimeningesapi.dto.AnswerDTO;
import fr.didiersenou.defimeningesapi.entity.Answer;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class AnswerMapper {

    public AnswerDTO toDTO(Answer entity) {
        if (entity == null) {
            return null;
        }

        UUID questionId = (entity.getQuestion() != null) ? entity.getQuestion().getId() : null;

        return new AnswerDTO(
                entity.getId(),
                questionId,
                entity.getLocale(),
                entity.getContent(),
                entity.isCorrect());
    }
}