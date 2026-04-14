package com.didiersenou.defimeningesapi.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import java.util.UUID;

/**
 * Entity class representing a Quiz Category.
 * * @version 1.0
 * 
 * @since 2026-04-13
 * @author Didier Senou
 */
@Entity
@Table(name = "categories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotBlank(message = "{validation.category.locale.required}")
    @Column(nullable = false, length = 5)
    private String locale;

    @NotBlank(message = "{validation.category.name.required}")
    @Column(nullable = false)
    private String name;

    @Positive(message = "{validation.category.defaultTimeLimit.positive}")
    @Column(name = "default_time_limit")
    private Integer defaultTimeLimit;

    public Category() {
    }

    public Category(String locale, String name, Integer defaultTimeLimit) {
        this.locale = locale;
        this.name = name;
        this.defaultTimeLimit = defaultTimeLimit;
    }

    // Getters and Setters
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getDefaultTimeLimit() {
        return defaultTimeLimit;
    }

    public void setDefaultTimeLimit(Integer defaultTimeLimit) {
        this.defaultTimeLimit = defaultTimeLimit;
    }
}