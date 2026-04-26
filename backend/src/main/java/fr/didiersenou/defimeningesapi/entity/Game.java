package fr.didiersenou.defimeningesapi.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import fr.didiersenou.defimeningesapi.enums.GameMode;
import fr.didiersenou.defimeningesapi.enums.GameStatus;

/**
 * Entity class representing a Game Session : Solo or Multiplayer
 * 
 * @version 1.0
 * @author Didier Senou
 */
@Entity
@Table(name = "games")
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotNull(message = "{validation.game.code.required}")
    @Column(name = "game_code", nullable = false, unique = true, length = 4)
    private String gameCode;

    @NotNull(message = "{validation.game.mode.required}")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private GameMode mode;

    @NotNull(message = "{validation.game.status.required}")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private GameStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @OneToMany(mappedBy = "game", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<GamePlayer> gamePlayers = new ArrayList<>();

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }

    public Game() {
    }

    public Game(String gameCode, GameMode mode, GameStatus status, Category category) {
        this.gameCode = gameCode;
        this.mode = mode;
        this.status = status;
        this.category = category;
    }

    // Getters and Setters
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getGameCode() {
        return gameCode;
    }

    public void setGameCode(String gameCode) {
        this.gameCode = gameCode;
    }

    public GameMode getMode() {
        return mode;
    }

    public void setMode(GameMode mode) {
        this.mode = mode;
    }

    public GameStatus getStatus() {
        return status;
    }

    public void setStatus(GameStatus status) {
        this.status = status;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<GamePlayer> getGamePlayers() {
        return gamePlayers;
    }

    public void setGamePlayers(List<GamePlayer> gamePlayers) {
        this.gamePlayers = gamePlayers;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}