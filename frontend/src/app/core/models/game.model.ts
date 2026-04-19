import { GameMode } from './game-mode.enum';
import { GameStatus } from './game-status.enum';

export interface Game {
    id: string;
    mode: GameMode;
    status: GameStatus;
    createdAt: Date | string;
}