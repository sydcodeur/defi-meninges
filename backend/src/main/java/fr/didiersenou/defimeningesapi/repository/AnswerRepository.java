package fr.didiersenou.defimeningesapi.repository;

import fr.didiersenou.defimeningesapi.entity.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, UUID> {
    List<Answer> findByQuestionId(UUID questionId);

    List<Answer> findByQuestionIdIn(List<UUID> questionIds);
}