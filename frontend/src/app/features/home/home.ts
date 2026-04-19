import { Component, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CardModule } from 'primeng/card';
import { ButtonModule } from 'primeng/button';
import { GameMode } from '../../core/models/game-mode.enum';
import { GameService } from '../../core/services/game.service';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [CommonModule, CardModule, ButtonModule],
  templateUrl: './home.html',
  styleUrl: './home.scss'
})
export class HomeComponent {
  private readonly gameService = inject(GameService);
  GameMode = GameMode;
  selectedMode: GameMode | null = null;

  selectMode(mode: GameMode): void {
    this.selectedMode = mode;
  }

  startGame(): void {
    if (this.selectedMode) {
      // TODO: Replace with real user ID from authentication
      const mockCreatorId = 'f264223e-8a1c-4388-b57a-341e4eb959e3';

      this.gameService.createGame(this.selectedMode, mockCreatorId).subscribe({
        next: (game) => {
          console.log('Game created successfully:', game);
          // TODO: Navigate to game page
        },
        error: (error) => {
          console.error('Error during game creation:', error);
          // TODO: Show user-friendly error message
        }
      });
    }
  }
}