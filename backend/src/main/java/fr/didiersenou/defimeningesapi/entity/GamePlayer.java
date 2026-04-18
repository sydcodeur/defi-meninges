package fr.didiersenou.defimeningesapi.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Entity class representing a User's participation in a Game.
 * 
 * @version 1.0
 * @author Didier Senou
 */
@Entity
@Table(name = "game_players")
public class GamePlayer {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotNull(message = "{validation.gameplayer.game.required}")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "game_id", nullable = false)
    private Game game;

    @NotNull(message = "{validation.gameplayer.user.required}")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false)
    private Integer score = 0;

    @Column(name = "joined_at", nullable = false, updatable = false)
    private LocalDateTime joinedAt;

    @PrePersist
    protected void onJoin() {
        joinedAt = LocalDateTime.now();
    }

    public GamePlayer() {
    }

    public GamePlayer(Game game, User user) {
        this.game = game;
        this.user = user;
    }

    // Getters and Setters
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public LocalDateTime getJoinedAt() {
        return joinedAt;
    }
}