package fr.didiersenou.defimeningesapi.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.UUID;

/**
 * Entity class representing a possible Answer to a Quiz Question.
 * 
 * @version 1.0
 * @author Didier Senou
 */
@Entity
@Table(name = "answers")
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotNull(message = "{validation.answer.question.required}")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id", nullable = false)
    private Question question;

    @NotBlank(message = "{validation.answer.locale.required}")
    @Column(nullable = false, length = 5)
    private String locale;

    @NotBlank(message = "{validation.answer.content.required}")
    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    @Column(name = "is_correct", nullable = false)
    private boolean isCorrect;

    public Answer() {
    }

    public Answer(Question question, String locale, String content, boolean isCorrect) {
        this.question = question;
        this.locale = locale;
        this.content = content;
        this.isCorrect = isCorrect;
    }

    // Getters and Setters
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
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

    public boolean isCorrect() {
        return isCorrect;
    }

    public void setCorrect(boolean isCorrect) {
        this.isCorrect = isCorrect;
    }
}