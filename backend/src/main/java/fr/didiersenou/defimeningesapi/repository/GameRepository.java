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
    
    @Query("SELECT g FROM Game g LEFT JOIN FETCH g.gamePlayers gp LEFT JOIN FETCH gp.user LEFT JOIN FETCH g.category WHERE g.gameCode = :gameCode")
    Optional<Game> findByGameCode(@Param("gameCode") String gameCode);
    
    @Query("SELECT g FROM Game g LEFT JOIN FETCH g.gamePlayers gp LEFT JOIN FETCH gp.user LEFT JOIN FETCH g.category WHERE g.id = :id")
    Optional<Game> findByIdWithPlayers(@Param("id") UUID id);
}