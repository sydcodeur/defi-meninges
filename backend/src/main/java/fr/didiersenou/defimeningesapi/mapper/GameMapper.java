package fr.didiersenou.defimeningesapi.mapper;

import fr.didiersenou.defimeningesapi.dto.GameDTO;
import fr.didiersenou.defimeningesapi.entity.Game;
import org.springframework.stereotype.Component;

@Component
public class GameMapper {

    public GameDTO toDTO(Game entity) {
        if (entity == null) {
            return null;
        }
        return new GameDTO(
                entity.getId(),
                entity.getMode(),
                entity.getStatus(),
                entity.getCreatedAt());
    }
}