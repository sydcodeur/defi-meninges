package com.didiersenou.defimeningesapi.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.util.UUID;

/**
 * Entity class representing a Quiz Question.
 * * @version 1.0
 * 
 * @author Didier Senou
 */
@Entity
@Table(name = "questions")
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotNull(message = "{validation.question.category.required}")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @NotBlank(message = "{validation.question.locale.required}")
    @Column(nullable = false, length = 5)
    private String locale;

    @NotBlank(message = "{validation.question.content.required}")
    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    @Positive(message = "{validation.question.difficulty.positive}")
    @Column(name = "difficulty_level")
    private Integer difficultyLevel;

    // Nullable, overrides category default if set
    @Positive(message = "{validation.question.timeLimit.positive}")
    @Column(name = "time_limit")
    private Integer timeLimit;

    public Question() {
    }

    public Question(Category category, String locale, String content, Integer difficultyLevel) {
        this.category = category;
        this.locale = locale;
        this.content = content;
        this.difficultyLevel = difficultyLevel;
    }

    // Getters and Setters
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getDifficultyLevel() {
        return difficultyLevel;
    }

    public void setDifficultyLevel(Integer difficultyLevel) {
        this.difficultyLevel = difficultyLevel;
    }

    public Integer getTimeLimit() {
        return timeLimit;
    }

    public void setTimeLimit(Integer timeLimit) {
        this.timeLimit = timeLimit;
    }
}