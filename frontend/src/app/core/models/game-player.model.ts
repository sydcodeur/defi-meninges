export interface Player {
    id: string;
    username: string;
}

export interface GamePlayer {
    id: string;
    gameId: string;
    user: Player;
    score: number;
}
