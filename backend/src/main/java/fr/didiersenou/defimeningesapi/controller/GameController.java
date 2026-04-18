package fr.didiersenou.defimeningesapi.controller;

import fr.didiersenou.defimeningesapi.dto.GameCreateRequest;
import fr.didiersenou.defimeningesapi.dto.GameDTO;
import fr.didiersenou.defimeningesapi.service.GameService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/games")
public class GameController {

    private final GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @PostMapping
    public ResponseEntity<GameDTO> createGame(@Valid @RequestBody GameCreateRequest request) {
        GameDTO gameDTO = gameService.createGame(request.mode(), request.creatorId());
        return ResponseEntity.status(HttpStatus.CREATED).body(gameDTO);
    }
}