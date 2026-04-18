package fr.didiersenou.defimeningesapi.repository;

import fr.didiersenou.defimeningesapi.entity.GamePlayer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface GamePlayerRepository extends JpaRepository<GamePlayer, UUID> {
    List<GamePlayer> findByGameId(UUID gameId);

    Optional<GamePlayer> findByGameIdAndUserId(UUID gameId, UUID userId);
}