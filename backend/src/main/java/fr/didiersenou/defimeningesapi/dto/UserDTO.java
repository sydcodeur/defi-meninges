package fr.didiersenou.defimeningesapi.dto;

import fr.didiersenou.defimeningesapi.enums.Role;
import java.util.UUID;

public record UserDTO(
        UUID id,
        String username,
        String email,
        Role role,
        Integer totalScore) {
}