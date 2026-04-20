package fr.didiersenou.defimeningesapi.service;

import fr.didiersenou.defimeningesapi.dto.GameDTO;
import fr.didiersenou.defimeningesapi.entity.Game;
import fr.didiersenou.defimeningesapi.entity.GamePlayer;
import fr.didiersenou.defimeningesapi.entity.User;
import fr.didiersenou.defimeningesapi.enums.GameMode;
import fr.didiersenou.defimeningesapi.enums.GameStatus;
import fr.didiersenou.defimeningesapi.mapper.GameMapper;
import fr.didiersenou.defimeningesapi.mapper.GamePlayerMapper;
import fr.didiersenou.defimeningesapi.repository.GamePlayerRepository;
import fr.didiersenou.defimeningesapi.repository.GameRepository;
import fr.didiersenou.defimeningesapi.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class GameService {

    private final GameRepository gameRepository;
    private final GamePlayerRepository gamePlayerRepository;
    private final UserRepository userRepository;
    private final GameMapper gameMapper;
    private final GamePlayerMapper gamePlayerMapper;

    public GameService(GameRepository gameRepository,
            GamePlayerRepository gamePlayerRepository,
            UserRepository userRepository,
            GameMapper gameMapper,
            GamePlayerMapper gamePlayerMapper) {
        this.gameRepository = gameRepository;
        this.gamePlayerRepository = gamePlayerRepository;
        this.userRepository = userRepository;
        this.gameMapper = gameMapper;
        this.gamePlayerMapper = gamePlayerMapper;
    }

    @Transactional
    public GameDTO createGame(GameMode mode, UUID creatorId) {
        User user = userRepository.findById(creatorId)
                .orElseThrow(() -> new IllegalArgumentException("Utilisateur introuvable"));

        Game game = new Game();
        game.setMode(mode);
        game.setStatus(GameStatus.WAITING);
        game = gameRepository.save(game);

        GamePlayer gamePlayer = new GamePlayer();
        gamePlayer.setGame(game);
        gamePlayer.setUser(user);
        gamePlayerRepository.save(gamePlayer);

        return gameMapper.toDTO(game);
    }
}