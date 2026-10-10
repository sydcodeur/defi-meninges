package fr.didiersenou.defimeningesapi.repository;

import fr.didiersenou.defimeningesapi.entity.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface GameRepository extends JpaRepository<Game, UUID> {

    @Query("""
            SELECT g FROM Game g
            JOIN FETCH g.host
            JOIN FETCH g.category
            LEFT JOIN FETCH g.gamePlayers gp
            LEFT JOIN FETCH gp.user
            WHERE g.gameCode = :gameCode
            ORDER BY gp.joinedAt
            """)
    Optional<Game> findByGameCode(@Param("gameCode") String gameCode);

    @Query("""
            SELECT g FROM Game g
            JOIN FETCH g.host
            JOIN FETCH g.category
            LEFT JOIN FETCH g.gamePlayers gp
            LEFT JOIN FETCH gp.user
            WHERE g.id = :id
            ORDER BY gp.joinedAt
            """)
    Optional<Game> findByIdWithPlayers(@Param("id") UUID id);

    boolean existsByGameCode(String gameCode);
}
