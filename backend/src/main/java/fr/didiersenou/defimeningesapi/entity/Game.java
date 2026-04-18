package fr.didiersenou.defimeningesapi.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.UUID;

import fr.didiersenou.defimeningesapi.entity.enums.GameMode;
import fr.didiersenou.defimeningesapi.entity.enums.GameStatus;

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

    @NotNull(message = "{validation.game.mode.required}")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private GameMode mode;

    @NotNull(message = "{validation.game.status.required}")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private GameStatus status;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }

    public Game() {
    }

    public Game(GameMode mode, GameStatus status) {
        this.mode = mode;
        this.status = status;
    }

    // Getters and Setters
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}