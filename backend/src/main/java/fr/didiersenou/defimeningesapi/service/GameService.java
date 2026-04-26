package fr.didiersenou.defimeningesapi.service;

import fr.didiersenou.defimeningesapi.dto.GameDTO;
import fr.didiersenou.defimeningesapi.dto.GamePlayerDTO;
import fr.didiersenou.defimeningesapi.entity.Category;
import fr.didiersenou.defimeningesapi.entity.Game;
import fr.didiersenou.defimeningesapi.entity.GamePlayer;
import fr.didiersenou.defimeningesapi.entity.User;
import fr.didiersenou.defimeningesapi.enums.GameMode;
import fr.didiersenou.defimeningesapi.enums.GameStatus;
import fr.didiersenou.defimeningesapi.exception.GameAlreadyStartedException;
import fr.didiersenou.defimeningesapi.exception.GameNotFoundException;
import fr.didiersenou.defimeningesapi.exception.ResourceNotFoundException;
import fr.didiersenou.defimeningesapi.mapper.GameMapper;
import fr.didiersenou.defimeningesapi.mapper.GamePlayerMapper;
import fr.didiersenou.defimeningesapi.repository.CategoryRepository;
import fr.didiersenou.defimeningesapi.repository.GamePlayerRepository;
import fr.didiersenou.defimeningesapi.repository.GameRepository;
import fr.didiersenou.defimeningesapi.repository.UserRepository;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.SecureRandom;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class GameService {

    private static final String GAME_CODE_CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final int GAME_CODE_LENGTH = 4;
    private static final SecureRandom random = new SecureRandom();

    private final GameRepository gameRepository;
    private final GamePlayerRepository gamePlayerRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    private final GameMapper gameMapper;
    private final GamePlayerMapper gamePlayerMapper;
    private final EntityManager entityManager;

    public GameService(GameRepository gameRepository,
            GamePlayerRepository gamePlayerRepository,
            UserRepository userRepository,
            CategoryRepository categoryRepository,
            GameMapper gameMapper,
            GamePlayerMapper gamePlayerMapper,
            EntityManager entityManager) {
        this.gameRepository = gameRepository;
        this.gamePlayerRepository = gamePlayerRepository;
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
        this.gameMapper = gameMapper;
        this.gamePlayerMapper = gamePlayerMapper;
        this.entityManager = entityManager;
    }

    @Transactional
    public GameDTO createGame(UUID userId, GameMode mode, UUID categoryId) {
        User creator = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));

        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with id: " + categoryId));

        String gameCode = generateUniqueGameCode();

        Game game = new Game();
        game.setGameCode(gameCode);
        game.setMode(mode);
        game.setStatus(GameStatus.WAITING);
        game.setCategory(category);
        game = gameRepository.save(game);

        GamePlayer gamePlayer = new GamePlayer();
        gamePlayer.setGame(game);
        gamePlayer.setUser(creator);
        gamePlayer.setScore(0);
        gamePlayerRepository.save(gamePlayer);

        // Force flush and clear to ensure database sync
        entityManager.flush();
        entityManager.clear();

        // Reload with players using JOIN FETCH (fresh from database)
        game = gameRepository.findByIdWithPlayers(game.getId()).orElseThrow();

        return gameMapper.toDTO(game);
    }

    @Transactional
    public GameDTO joinGame(UUID userId, String gameCode) {
        Game game = gameRepository.findByGameCode(gameCode)
                .orElseThrow(() -> new GameNotFoundException("Game not found with code: " + gameCode));

        if (game.getStatus() != GameStatus.WAITING) {
            throw new GameAlreadyStartedException("Cannot join game that has already started");
        }

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));

        // Check if user already joined
        boolean alreadyJoined = game.getGamePlayers().stream()
                .anyMatch(gp -> gp.getUser().getId().equals(userId));

        if (!alreadyJoined) {
            GamePlayer gamePlayer = new GamePlayer();
            gamePlayer.setGame(game);
            gamePlayer.setUser(user);
            gamePlayer.setScore(0);
            gamePlayerRepository.save(gamePlayer);

            // Force flush and clear to ensure database sync
            entityManager.flush();
            entityManager.clear();

            // Reload with updated players using JOIN FETCH (fresh from database)
            game = gameRepository.findByIdWithPlayers(game.getId()).orElseThrow();
        }

        return gameMapper.toDTO(game);
    }

    @Transactional(readOnly = true)
    public GameDTO getGameByCode(String gameCode) {
        Game game = gameRepository.findByGameCode(gameCode)
                .orElseThrow(() -> new GameNotFoundException("Game not found with code: " + gameCode));
        return gameMapper.toDTO(game);
    }

    @Transactional(readOnly = true)
    public List<GamePlayerDTO> getGamePlayers(String gameCode) {
        Game game = gameRepository.findByGameCode(gameCode)
                .orElseThrow(() -> new GameNotFoundException("Game not found with code: " + gameCode));

        return game.getGamePlayers().stream()
                .map(gamePlayerMapper::toDTO)
                .collect(Collectors.toList());
    }

    private String generateUniqueGameCode() {
        String code;
        int attempts = 0;
        final int maxAttempts = 10;

        do {
            code = generateRandomCode();
            attempts++;
        } while (gameRepository.findByGameCode(code).isPresent() && attempts < maxAttempts);

        if (attempts >= maxAttempts) {
            throw new RuntimeException("Failed to generate unique game code after " + maxAttempts + " attempts");
        }

        return code;
    }

    private String generateRandomCode() {
        StringBuilder code = new StringBuilder(GAME_CODE_LENGTH);
        for (int i = 0; i < GAME_CODE_LENGTH; i++) {
            code.append(GAME_CODE_CHARS.charAt(random.nextInt(GAME_CODE_CHARS.length())));
        }
        return code.toString();
    }
}