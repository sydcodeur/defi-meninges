package fr.didiersenou.defimeningesapi.mapper;

import fr.didiersenou.defimeningesapi.dto.GamePlayerDTO;
import fr.didiersenou.defimeningesapi.dto.PlayerDTO;
import fr.didiersenou.defimeningesapi.entity.GamePlayer;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class GamePlayerMapper {

    private final UserMapper userMapper;

    public GamePlayerMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public GamePlayerDTO toDTO(GamePlayer entity) {
        if (entity == null) {
            return null;
        }

        UUID gameId = (entity.getGame() != null) ? entity.getGame().getId() : null;
        PlayerDTO player = userMapper.toPlayerDTO(entity.getUser());

        return new GamePlayerDTO(
                entity.getId(),
                gameId,
                player,
                entity.getScore());
    }
}