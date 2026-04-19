import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CardModule } from 'primeng/card';
import { ButtonModule } from 'primeng/button';
import { GameMode } from '../../core/models/game-mode.enum';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [CommonModule, CardModule, ButtonModule],
  templateUrl: './home.html',
  styleUrl: './home.scss'
})
export class HomeComponent {
  GameMode = GameMode;

  selectedMode: GameMode | null = null;

  selectMode(mode: GameMode): void {
    this.selectedMode = mode;
  }

  startGame(): void {
    if (this.selectedMode) {
      console.log('Démarrage de la partie en mode:', this.selectedMode);
    }
  }
}