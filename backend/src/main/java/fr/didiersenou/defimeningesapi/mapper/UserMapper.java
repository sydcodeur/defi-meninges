package fr.didiersenou.defimeningesapi.mapper;

import fr.didiersenou.defimeningesapi.dto.UserDTO;
import fr.didiersenou.defimeningesapi.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserDTO toDTO(User entity) {
        if (entity == null) {
            return null;
        }
        return new UserDTO(
                entity.getId(),
                entity.getUsername(),
                entity.getEmail(),
                entity.getRole(),
                entity.getTotalScore());
    }
}