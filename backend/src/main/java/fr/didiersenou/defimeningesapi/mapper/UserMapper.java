package fr.didiersenou.defimeningesapi.mapper;

import fr.didiersenou.defimeningesapi.dto.PlayerDTO;
import fr.didiersenou.defimeningesapi.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public PlayerDTO toPlayerDTO(User entity) {
        if (entity == null) {
            return null;
        }
        return new PlayerDTO(entity.getId(), entity.getUsername());
    }
}
