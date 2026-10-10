import { GameMode } from './game-mode.enum';
import { GameStatus } from './game-status.enum';
import { GamePlayer } from './game-player.model';

export interface Game {
    id: string;
    gameCode: string;
    mode: GameMode;
    status: GameStatus;
    categoryName: string;
    hostId: string;
    players: GamePlayer[];
    createdAt: string;
}

export interface GameCreateRequest {
    mode: GameMode;
    categoryId: string;
}
