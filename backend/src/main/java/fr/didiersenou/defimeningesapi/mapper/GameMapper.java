package fr.didiersenou.defimeningesapi.mapper;

import fr.didiersenou.defimeningesapi.dto.GameDTO;
import fr.didiersenou.defimeningesapi.dto.GamePlayerDTO;
import fr.didiersenou.defimeningesapi.entity.Game;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class GameMapper {

    private final GamePlayerMapper gamePlayerMapper;

    public GameMapper(GamePlayerMapper gamePlayerMapper) {
        this.gamePlayerMapper = gamePlayerMapper;
    }

    public GameDTO toDTO(Game entity) {
        if (entity == null) {
            return null;
        }

        List<GamePlayerDTO> playerDTOs = entity.getGamePlayers() != null
                ? entity.getGamePlayers().stream()
                        .map(gamePlayerMapper::toDTO)
                        .collect(Collectors.toList())
                : List.of();

        return new GameDTO(
                entity.getId(),
                entity.getGameCode(),
                entity.getMode(),
                entity.getStatus(),
                entity.getCategory() != null ? entity.getCategory().getName() : null,
                !entity.getGamePlayers().isEmpty() ? entity.getGamePlayers().get(0).getUser().getId() : null,
                playerDTOs,
                entity.getCreatedAt());
    }
}