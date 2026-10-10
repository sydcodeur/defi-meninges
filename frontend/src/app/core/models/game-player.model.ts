import { User } from './user.model';

export interface GamePlayer {
    id: string;
    gameId: string;
    user: User;
    score: number;
}
