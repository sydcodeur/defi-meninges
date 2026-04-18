package fr.didiersenou.defimeningesapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.didiersenou.defimeningesapi.entity.Question;

import java.util.List;
import java.util.UUID;

@Repository
public interface QuestionRepository extends JpaRepository<Question, UUID> {
    List<Question> findByCategoryId(UUID categoryId);
}