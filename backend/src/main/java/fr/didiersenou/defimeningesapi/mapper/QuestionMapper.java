package fr.didiersenou.defimeningesapi.mapper;

import fr.didiersenou.defimeningesapi.dto.AnswerDTO;
import fr.didiersenou.defimeningesapi.dto.CategoryDTO;
import fr.didiersenou.defimeningesapi.dto.QuestionDTO;
import fr.didiersenou.defimeningesapi.entity.Answer;
import fr.didiersenou.defimeningesapi.entity.Question;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class QuestionMapper {

    private final CategoryMapper categoryMapper;
    private final AnswerMapper answerMapper;

    public QuestionMapper(CategoryMapper categoryMapper, AnswerMapper answerMapper) {
        this.categoryMapper = categoryMapper;
        this.answerMapper = answerMapper;
    }

    public QuestionDTO toDTO(Question entity, List<Answer> answers) {
        if (entity == null) {
            return null;
        }

        CategoryDTO categoryDTO = categoryMapper.toDTO(entity.getCategory());

        List<AnswerDTO> answerDTOs = null;
        if (answers != null) {
            answerDTOs = answers.stream()
                    .map(answerMapper::toDTO)
                    .collect(Collectors.toList());
        }

        return new QuestionDTO(
                entity.getId(),
                categoryDTO,
                entity.getLocale(),
                entity.getContent(),
                entity.getDifficultyLevel(),
                entity.getTimeLimit(),
                answerDTOs);
    }
}