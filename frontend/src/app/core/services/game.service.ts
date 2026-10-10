import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Game, GameCreateRequest } from '../models/game.model';
import { environment } from '../../../environments/environment';

@Injectable({
    providedIn: 'root'
})
export class GameService {
    private readonly http = inject(HttpClient);
    private readonly apiUrl = `${environment.apiUrl}/games`;

    createGame(request: GameCreateRequest): Observable<Game> {
        return this.http.post<Game>(this.apiUrl, request);
    }
}
