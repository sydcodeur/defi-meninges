package com.didiersenou.defimeningesapi.dto;

import jakarta.validation.constraints.NotBlank;

// Record pour exposer une catégorie de façon allégée
public record CategoryDTO(
    Long id,
    @NotBlank(message = "Le nom de la catégorie est obligatoire") 
    String name,
    String description
) {}