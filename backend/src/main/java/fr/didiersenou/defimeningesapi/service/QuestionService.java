package fr.didiersenou.defimeningesapi.service;

import fr.didiersenou.defimeningesapi.dto.QuestionDTO;
import fr.didiersenou.defimeningesapi.entity.Answer;
import fr.didiersenou.defimeningesapi.entity.Question;
import fr.didiersenou.defimeningesapi.mapper.QuestionMapper;
import fr.didiersenou.defimeningesapi.repository.AnswerRepository;
import fr.didiersenou.defimeningesapi.repository.QuestionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class QuestionService {

    private final QuestionRepository questionRepository;
    private final AnswerRepository answerRepository;
    private final QuestionMapper questionMapper;

    public QuestionService(QuestionRepository questionRepository,
            AnswerRepository answerRepository,
            QuestionMapper questionMapper) {
        this.questionRepository = questionRepository;
        this.answerRepository = answerRepository;
        this.questionMapper = questionMapper;
    }

    @Transactional(readOnly = true)
    public List<QuestionDTO> getQuestionsByCategory(UUID categoryId) {
        List<Question> questions = questionRepository.findByCategoryId(categoryId);

        if (questions.isEmpty()) {
            return Collections.emptyList();
        }

        List<UUID> questionIds = questions.stream()
                .map(Question::getId)
                .collect(Collectors.toList());

        List<Answer> allAnswers = answerRepository.findByQuestionIdIn(questionIds);

        Map<UUID, List<Answer>> answersByQuestionId = allAnswers.stream()
                .collect(Collectors.groupingBy(answer -> answer.getQuestion().getId()));

        return questions.stream()
                .map(question -> {
                    List<Answer> questionAnswers = answersByQuestionId.getOrDefault(question.getId(),
                            Collections.emptyList());
                    return questionMapper.toDTO(question, questionAnswers);
                })
                .collect(Collectors.toList());
    }
}