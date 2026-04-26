package fr.didiersenou.defimeningesapi.controller;

import fr.didiersenou.defimeningesapi.dto.GameCreateRequest;
import fr.didiersenou.defimeningesapi.dto.GameDTO;
import fr.didiersenou.defimeningesapi.dto.GamePlayerDTO;
import fr.didiersenou.defimeningesapi.dto.JoinGameRequest;
import fr.didiersenou.defimeningesapi.security.CustomUserDetails;
import fr.didiersenou.defimeningesapi.service.GameService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/games")
public class GameController {

    private final GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public GameDTO createGame(@Valid @RequestBody GameCreateRequest request,
            @AuthenticationPrincipal CustomUserDetails userDetails) {
        return gameService.createGame(userDetails.getUser().getId(), request.mode(), request.categoryId());
    }

    @PostMapping("/join")
    public GameDTO joinGame(@Valid @RequestBody JoinGameRequest request,
            @AuthenticationPrincipal CustomUserDetails userDetails) {
        return gameService.joinGame(userDetails.getUser().getId(), request.gameCode());
    }

    @GetMapping("/{gameCode}")
    public GameDTO getGame(@PathVariable String gameCode) {
        return gameService.getGameByCode(gameCode);
    }

    @GetMapping("/{gameCode}/players")
    public List<GamePlayerDTO> getPlayers(@PathVariable String gameCode) {
        return gameService.getGamePlayers(gameCode);
    }
}