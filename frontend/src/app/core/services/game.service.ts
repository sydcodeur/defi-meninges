import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { Game } from '../models/game.model';
import { GameMode } from '../models/game-mode.enum';
import { environment } from '../../../environments/environment';

interface CreateGameRequest {
    mode: GameMode;
    creatorId: string;
}

@Injectable({
    providedIn: 'root'
})
export class GameService {
    private readonly API_URL = environment.apiUrl;

    constructor(private http: HttpClient) { }

    createGame(mode: GameMode, creatorId: string): Observable<Game> {
        const requestBody: CreateGameRequest = {
            mode,
            creatorId
        };

        return this.http
            .post<Game>(`${this.API_URL}/games`, requestBody)
            .pipe(
                catchError((error: HttpErrorResponse) => {
                    console.error('Failed to create game:', error);
                    return throwError(() => new Error('Game creation failed. Please try again.'));
                })
            );
    }
}