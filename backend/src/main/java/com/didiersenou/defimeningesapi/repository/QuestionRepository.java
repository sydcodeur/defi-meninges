package com.didiersenou.defimeningesapi.repository;

import com.didiersenou.defimeningesapi.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface QuestionRepository extends JpaRepository<Question, UUID> {
    List<Question> findByCategoryId(UUID categoryId);
}