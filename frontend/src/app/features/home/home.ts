import { Component, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';
import { CardModule } from 'primeng/card';
import { ButtonModule } from 'primeng/button';
import { GameMode } from '../../core/models/game-mode.enum';
import { GameService } from '../../core/services/game.service';
import { AuthService } from '../../core/services/auth.service';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [CommonModule, CardModule, ButtonModule],
  templateUrl: './home.html',
  styleUrl: './home.scss'
})
export class HomeComponent {
  private readonly gameService = inject(GameService);
  private readonly authService = inject(AuthService);
  private readonly router = inject(Router);

  GameMode = GameMode;
  selectedMode: GameMode | null = null;

  readonly isAuthenticated = this.authService.isAuthenticated;
  readonly currentUser = this.authService.currentUser;

  selectMode(mode: GameMode): void {
    this.selectedMode = mode;
    console.log('Mode sélectionné:', mode);
  }

  startGame(): void {
    if (!this.selectedMode) {
      return;
    }

    if (!this.isAuthenticated()) {
      localStorage.setItem('redirect_after_login', '/lobby');
      this.router.navigate(['/login']);
      return;
    }

    this.router.navigate(['/lobby']);
  }
}