package fr.didiersenou.defimeningesapi.mapper;

import fr.didiersenou.defimeningesapi.dto.GamePlayerDTO;
import fr.didiersenou.defimeningesapi.dto.UserDTO;
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
        UserDTO userDTO = userMapper.toDTO(entity.getUser());

        return new GamePlayerDTO(
                entity.getId(),
                gameId,
                userDTO,
                entity.getScore());
    }
}